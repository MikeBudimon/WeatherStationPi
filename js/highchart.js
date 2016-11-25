$(document).ready(function () {

    var temperature = [];
    var outsideTemp = [];
    var humidity = [];
    var outsideHum = [];
    var cputemp = [];
    var myDateFormat = "%H:%M %d/%m/%Y";

    // get json data
    $.getJSON("sensordata.json", function (data) {

        $.each(data, function (i, value) {

            // to unix-timestamp, save your sensordata with timestamp in array	
            temperature[i] = [new Date(value.Timestamp.replace(' ', 'T') + 'Z').getTime(), parseFloat(value.Temperature)];
            outsideTemp[i] = [new Date(value.Timestamp.replace(' ', 'T') + 'Z').getTime(), parseFloat(value.OutsideTemp)];
            humidity[i] = [new Date(value.Timestamp.replace(' ', 'T') + 'Z').getTime(), parseFloat(value.Humidity)];
            outsideHum[i] = [new Date(value.Timestamp.replace(' ', 'T') + 'Z').getTime(), parseFloat(value.OutsideHum)];
            cputemp[i] = [new Date(value.Timestamp.replace(' ', 'T') + 'Z').getTime(), parseFloat(value.CPUTemp)];
        });

        // show current data
        document.getElementById("curTemp").innerHTML = "<b>Current Temperature:&emsp;</b>Room = <b>" + temperature[temperature.length - 1][1] + " °C</b>&emsp;Outside = <b>" + outsideTemp[outsideTemp.length - 1][1] + " °C</b>";
        document.getElementById("curHum").innerHTML = "<b>Current Humidity:&emsp;</b>Room = <b>" + humidity[humidity.length - 1][1] + " %</b>&emsp;Outside = <b>" + outsideHum[outsideHum.length - 1][1] + " %</b>";

        // standard settings for graph
        var options = {
            chart: {
                renderTo: "containerGraph",
                zoomType: "x",
            },

            title: {
                text: "Temperature in the past 24 hours"
            },
            subtitle: {
                text: "Click and drag in the plot area to zoom in"
            },

            yAxis: {
                title: {
                    text: "Temperature ( °C )"
                },
            },
            tooltip: {
                valueSuffix: ' °C'
            },
            xAxis: {
                type: "datetime",
                dateTimeLabelFormats: {
                    millisecond: myDateFormat,
                    second: myDateFormat,
                    minute: myDateFormat,
                    hour: myDateFormat,
                    day: myDateFormat,
                    week: myDateFormat,
                    month: myDateFormat,
                    year: myDateFormat
                },
                min: temperature[temperature.length - 1][0] - 24 * 3600 * 1000,
                max: temperature[temperature.length - 1][0]
            },
            series: []
        };

        // create graph and add dataseries
        var chart = new Highcharts.Chart(options);

        chart.addSeries({
            name: "Room",
            data: temperature,
        }, false);
        chart.addSeries({
            name: "Raspberry Pi",
            data: cputemp,
        }, false);
        /*
                chart.addSeries({
                    name: "Outside",
                    data: outsideTemp,
                }, false);
        */
        chart.redraw();


        // change graph resolution and options
        $("#T-last24h").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }

            chart.xAxis[0].update({ min: temperature[temperature.length - 1][0] - 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: temperature[temperature.length - 1][0] });
            chart.setTitle({ text: "Temperature in the past 24 hours" });
            chart.yAxis[0].setTitle({ text: "Temperature ( °C )" });

            chart.addSeries({
                name: "Room",
                data: temperature,
            }, false);

            chart.addSeries({
                name: "Raspberry Pi",
                data: cputemp,
            }, false);
            /*
                        chart.addSeries({
                            name: "Outside",
                            data: outsideTemp,
                        }, false);
            */
            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[2].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });

            chart.redraw();
        });


        $("#T-lastWeek").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }


            chart.xAxis[0].update({ min: temperature[temperature.length - 1][0] - 7 * 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: temperature[temperature.length - 1][0] });
            chart.setTitle({ text: "Temperature in the past week" });
            chart.yAxis[0].setTitle({ text: "Temperature ( °C )" });

            chart.addSeries({
                name: "Room",
                data: temperature,
            }, false);

            chart.addSeries({
                name: "Raspberry Pi",
                data: cputemp,
            }, false);
            /*
                        chart.addSeries({
                            name: "Outside",
                            data: outsideTemp,
                        }, false);
            */
            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[2].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });

            chart.redraw();
        });


        $("#T-lastMonth").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }

            chart.xAxis[0].update({ min: temperature[temperature.length - 1][0] - 4 * 7 * 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: temperature[temperature.length - 1][0] });
            chart.setTitle({ text: "Temperature in the past month" });
            chart.yAxis[0].setTitle({ text: "Temperature ( °C )" });

            chart.addSeries({
                name: "Room",
                data: temperature,
            }, false);

            chart.addSeries({
                name: "Raspberry Pi",
                data: cputemp,
            }, false);
            /*
                        chart.addSeries({
                            name: "Outside",
                            data: outsideTemp,
                        }, false);
            */
            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[2].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });

            chart.redraw();
        });


        $("#T-lastYear").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }

            chart.xAxis[0].update({ min: temperature[temperature.length - 1][0] - 12 * 4 * 7 * 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: temperature[temperature.length - 1][0] });
            chart.setTitle({ text: "Temperature in the past year" });
            chart.yAxis[0].setTitle({ text: "Temperature ( °C )" });

            chart.addSeries({
                name: "Room",
                data: temperature,
            }, false);

            chart.addSeries({
                name: "Raspberry Pi",
                data: cputemp,
            }, false);
            /*
                        chart.addSeries({
                            name: "Outside",
                            data: outsideTemp,
                        }, false);
            */
            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });
            chart.series[2].update({
                tooltip: {
                    valueSuffix: ' °C',
                }
            });

            chart.redraw();
        });

        $("#H-last24h").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }

            chart.xAxis[0].update({ min: humidity[humidity.length - 1][0] - 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: humidity[humidity.length - 1][0] });
            chart.setTitle({ text: "Humidity in the past 24 hours" });
            chart.yAxis[0].setTitle({ text: "Humidity ( % )" });

            chart.addSeries({
                name: "Room",
                data: humidity,
            }, false);

            chart.addSeries({
                name: "Outside",
                data: outsideHum,
            }, false);

            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });

            chart.redraw();
        });


        $("#H-lastWeek").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }

            chart.xAxis[0].update({ min: humidity[humidity.length - 1][0] - 7 * 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: humidity[humidity.length - 1][0] });
            chart.setTitle({ text: "Humidity in the past week" });
            chart.yAxis[0].setTitle({ text: "Humidity ( % )" });

            chart.addSeries({
                name: "Room",
                data: humidity,
            }, false);

            chart.addSeries({
                name: "Outside",
                data: outsideHum,
            }, false);

            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });

            chart.redraw();
        });


        $("#H-lastMonth").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }

            chart.xAxis[0].update({ min: humidity[humidity.length - 1][0] - 4 * 7 * 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: humidity[humidity.length - 1][0] });
            chart.setTitle({ text: "Humidity in the past month" });
            chart.yAxis[0].setTitle({ text: "Humidity ( % )" });

            chart.addSeries({
                name: "Room",
                data: humidity,
            }, false);

            chart.addSeries({
                name: "Outside",
                data: outsideHum,
            }, false);

            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });

            chart.redraw();
        });

        $("#H-lastYear").click(function () {

            while (chart.series.length > 0) {
                chart.series[0].remove(false);
            }

            chart.xAxis[0].update({ min: humidity[humidity.length - 1][0] - 12 * 4 * 7 * 24 * 3600 * 1000 });
            chart.xAxis[0].update({ max: humidity[humidity.length - 1][0] });
            chart.setTitle({ text: "Humidity in the past year" });
            chart.yAxis[0].setTitle({ text: "Humidity ( % )" });

            chart.addSeries({
                name: "Room",
                data: humidity,
            }, false);

            chart.addSeries({
                name: "Outside",
                data: outsideHum,
            }, false);

            chart.series[0].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });
            chart.series[1].update({
                tooltip: {
                    valueSuffix: ' %',
                }
            });

            chart.redraw();
        });

    });

});
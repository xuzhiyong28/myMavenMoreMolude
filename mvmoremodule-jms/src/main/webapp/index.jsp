<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>WebSocket client</title>
    <script type="text/javascript">
        var socket;
        if (typeof (WebSocket) == "undefined"){
            alert("This explorer don't support WebSocket")
        }

        function connect() {
            //Connect WebSocket server
            socket =new WebSocket("ws://127.0.0.1:8080/wbSocket");
            //open
            socket.onopen = function () {
                alert("WebSocket is open");
            }
            //Get message
            socket.onmessage = function (msg) {
                alert("Message is " + msg.data);
            }
            //close
            socket.onclose = function () {
                alert("WebSocket is closed");
            }
            //error
            socket.onerror = function (e) {
                alert("Error is " + e);
            }
        }

        function close() {
            socket.close();
        }

        function sendMsg() {
            socket.send("This is a client message ");
        }
    </script>
</head>
<body>
<button onclick="connect()">connect</button>
<button onclick="close()">close</button>
<button onclick="sendMsg()">sendMsg</button>
</body>
</html>
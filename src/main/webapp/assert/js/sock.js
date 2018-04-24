var wsServer = 'ws://127.0.0.1:8080/thesis/chat';
var websocket = new WebSocket(wsServer);
websocket.onopen = function (evt) {
    onOpen(evt)
};
websocket.onclose = function (evt) {
    onClose(evt)
};
websocket.onmessage = function (evt) {
    onMessage(evt)
};
websocket.onerror = function (evt) {
    onError(evt)
};

function onOpen(evt) {
    console.log("Connected to WebSocket server.");
}

function onClose(evt) {
    console.log("Disconnected");
}

function onMessage(evt) {
    console.log('Retrieved data from server: ' + evt.data);
}

function onError(evt) {
    console.log('Error occured: ' + evt.data);
}

websocket.send("test");//客户端向服务器发送消息
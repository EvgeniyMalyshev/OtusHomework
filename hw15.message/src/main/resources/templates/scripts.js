const messageWindow = document.getElementById("messages");
const sendButton = document.getElementById("send");
const messageInput = document.getElementById("message");

// Создание WebSocket. Открытие соединения
const socket = new WebSocket("ws://localhost:8080/admin_create");
socket.binaryType = "arraybuffer";

sendButton.onclick = function () {
    sendMessage(messageInput.value);
    messageInput.value = "";
};

function sendMessage(user) {
    socket.send(user);
}


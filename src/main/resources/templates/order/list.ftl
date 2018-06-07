<!DOCTYPE html>
<html lang="zh-CN">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=utf-8">
        <title>卖家订单</title>
    </head>
    <body>
        <h1>卖家订单</h1>
        <div>
            <table>
                <tr>
                    <td>orderId</td>
                    <td>买家名称</td>
                    <td>买家手机号码</td>
                    <td>买家地址</td>
                </tr>
                <#list orderMasterDTOPage.content as orderMasterDTO>
                    <td>${orderMasterDTO.orderId}</td>
                    <td>${orderMasterDTO.buyerName}</td>
                    <td>${orderMasterDTO.buyerPhone}</td>
                    <td>${orderMasterDTO.buyerAddress}</td>
                </#list>
            </table>
        </div>
    </body>
    <script>
        //websocket消息通信
        var webSocket = null;

        //判断当前浏览器是否支持WebSocket
        if('WebSocket' in window){
            webSocket = new WebSocket('ws://localhost:8080/sell/websocket');
        }else {
            alert("该浏览器不支持WebSocket");
        }

        //连接成功建立的回调方法
        webSocket.onopen = function (event) {
            console.log("建立连接");
        }

        //连接关闭的回调方法
        webSocket.onclose = function (event) {
            console.log("断开连接");
        }

        //接收到消息的回调方法
        webSocket.onmessage = function (event) {
            console.log("收到消息：" + event.data);
            alert(event.data);
        }

        //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
        webSocket.onbeforeunload = function(){
            closeWebSocket();
        }

        //连接发生错误的回调方法
        websocket.onerror = function (){
            console.log("WebSocket连接发生错误");
        }

        //关闭连接
        function closeWebSocket(){
            websocket.close();
        }

        //发送消息
        function send(){
            var message = document.getElementById('text').value;
            websocket.send(message);
        }

    </script>
</html>
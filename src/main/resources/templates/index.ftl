<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
</head>
<body>
<a href="/download/papers/abc.doc">点击链接下载</a>
<button onclick="download1()">点击按钮下载</button>
<button onclick="download2">点击按钮下载</button>

<script>
    // 会打开一个空白页下载，然后空白页消失，用户体验不好
    /*function download1() {
        window.open('/download/papers/1');
    }
    // 直接下载，用户体验好
    function download2() {
        var $form = $('<form method="GET"></form>');
        $form.attr('action', '/download/papers/1');
        $form.appendTo($('body'));
        $form.submit();
    }*/
</script>

<form action="/fileUpload" method="post" enctype="multipart/form-data">
    <p>选择文件: <input type="file" name="fileName"/></p>
    <p><input type="submit" value="提交"/></p>
</form>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sweater</title>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>
</head>
<body>
<div>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="submit" value="Sign Out"/>
    </form>
</div>
<div>
    <span> <a href="/user">User List!</a></span>
</div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="Введите сообщение" />
        <input type="text" name="tag" placeholder="Тэг" />
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>
<div>Список сообщений</div>
<form method="get" action="/main">
    <label>
        <input type="text" name="filter" value="${filter!}">
    </label>
    <button type="submit">Найти</button>
</form>
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <strong>${message.getAuthorName()}</strong>
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}">
            </#if>
        </div>
    </div>
    <#else >
    <strong>No messages!</strong>
    </#list>
</body>
</html>

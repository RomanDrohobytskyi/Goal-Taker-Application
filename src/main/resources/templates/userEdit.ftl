<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Spring Security Example </title>
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/css/buttons.css">
    <script type="text/javascript" src="/static/javascript/js.js"></script>
</head>
<body>
    <div>
        User editor.
    </div>
    <form action="/user" method="post">
        <input type="text" name="username" value="${user.username}">
        <#list roles as role>
        <div>
            <label>
                <input type="checkbox" name="${role}"
                    ${user.roles?seq_contains(role)?string("checked", "")}>
                ${role}
            </label>
        </div>
        </#list>
        <input type="hidden" value="${user.id}" name="userId">
        <input type="hidden" value="${_csrf.token}" name="_csrf">
        <button type="submit">Save</button>
    </form>
</body>
</html>
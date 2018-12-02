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
        List of Users.
        <table>
                <tr>
                    <th>Name</th>
                    <th>Role</th>
                    <th></th>
                </tr>

            <tbody>
        <#list users as user>
        <tr>
            <td>
                ${user.username}
            </td>
            <td><#list user.roles as role>
                ${role}<#sep>,
            </#list>
            </td>
            <td> <a href="/user/${user.id}"> Edit</a> </td>
        </tr>
        </#list>
            </tbody>
        </table>
    </div>
</body>
</html>
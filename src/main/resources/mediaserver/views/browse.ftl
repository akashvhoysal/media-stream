<#-- @ftlvariable name="" type="mediaserver.views" -->
<html>
    <body>
    <h2>Files</h2>
        <ul>
            <#list fileDisplay.getFiles() as file>
                <li>
                    <a href="/stream/${fileDisplay.getPath()}${file}">${file}</a>
                </li>
            </#list>
        </ul>

    <h2>Folders</h2>
            <ul>
                <#list fileDisplay.getFolders() as file>
                    <li>
                        <a href="/browse/${fileDisplay.getPath()}%2F${file}">${file}</a>
                    </li>
                </#list>
            </ul>
    </body>
</html>
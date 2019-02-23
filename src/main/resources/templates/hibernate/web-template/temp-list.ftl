<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
</head>
<body>

<div class="entityTable" style="padding: 10px">
    <form id="head-form" class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">ID</label>
                <div class="layui-input-inline">
                    <input name="id"  class="layui-input" type="text">
                </div>
            </div>
        </div>
        <span class="layui-btn" id="search-btn" data-type="reload">搜索</span>
        <a class="layui-btn" href="add" >新增</a>
    </form>
</div>

<table class="layui-hide" id="LAY_TABLE_ENTITY" lay-filter="entity"></table>


<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('table', function(){
        var table = layui.table;

        //方法级渲染
        table.render({
            elem: '#LAY_TABLE_ENTITY'
            ,url: 'grid'
            ,cols: [[
                {field:'id', title: 'ID', width:80, sort: true, fixed: true}
            <#list columnList as column>
                <#if column.visible && !column.hideOnPage >
                 ,{field:'${column.javaName}', title: '${column.comment}'}
                </#if>
            </#list>
            ],]
            ,id: 'tableReload'
            ,page: true
        });

        var $ = layui.$, active = {
            reload: function(){
                //执行重载
                table.reload('tableReload', {
                    where: $("form#head-form").serializeObject()
                    ,page: {
                        curr: 1 //重新从第 1 页开始
                    },
                });
            }
        };

        $('#search-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });
    });
</script>

</body>
</html>
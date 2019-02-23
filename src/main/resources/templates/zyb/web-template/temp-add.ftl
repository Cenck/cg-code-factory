<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8"/>
    <title>${comment}添加</title>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <script type="text/javascript" th:src="${r'${baseContext.staticBase}'}+'/lib/jquery/jquery-3.2.1.min.js'"></script>
    <script type="text/javascript" th:src="${r'${baseContext.staticBase}'}+'/lib/layui/v2.3.0/layui.js'"></script>
    <script type="text/javascript" th:src="${r'${baseContext.staticBase}'}+'/js/wa.jquery.plus.js'"></script>
    <link rel="stylesheet" th:href="${r'${baseContext.staticBase}'}+'/lib/layui/v2.3.0/css/layui.css'">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>添加${comment}</legend>
</fieldset>
<form class="layui-form" th:action="@{insert}" lay-filter="addForm">

<#list columnList as column>
<#if column.visible && !column.hideOnPage  >
    <div class="layui-form-item ">
        <label class="layui-form-label">${column.comment}</label>
        <div class="layui-input-block">
            <input placeholder="请输入${column.comment}" lay-verify="${column.name}" class="layui-input" name="${column.javaName}">
        </div>
    </div>
</#if>
</#list>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <span class="layui-btn" lay-submit="" lay-filter="add-btn">立即提交</span>
        </div>
    </div>
</form>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form
                , layer = layui.layer
                , layedit = layui.layedit
                , laydate = layui.laydate;

        //监听提交
        form.on('submit(add-btn)', function (data) {
            layer.confirm('是否提交？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                $.post("insert",data.field,function (data) {
                    if(data && data.success){
                        layer.msg('保存成功', {icon: 1});
                    }else layer.msg(data.message)
                },'json');
            });
        });

        //表单初始赋值
        form.val('addForm', {
        })
    });
</script>

</body>
</html>
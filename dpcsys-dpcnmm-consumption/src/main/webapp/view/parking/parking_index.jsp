<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>停车配置管理页面</title>
    <%@ include file="../common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/assets/css/parking/parking.css">
    <link rel="stylesheet" href="${ctx}/assets/css/daterangepicker-bs3.css">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="parking-wrapper">
                        <div class="parking-content">
                            <div class="container">
                                <div class="select-model">
                                    <div class="article select-menu">
                                        <h4 class="parking-select-title">选择停车方式：</h4>
                                        <input type="hidden" value="0" id="parkType"><%--默认长期--%>
                                        <input type="hidden" value="${error}" id="errorMsg"><%--有错误时的提示--%>
                                        <%--<input type="hidden" value="${ctx}/parking/permissionDenied" id="permission_denied">权限不足--%>
                                        <a href="javascript:void(0)" class="disabled_hover" id="Long-term">
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosinline" id="optionsRadios3" value="option1" checked>长期停车
                                            </label>
                                        </a>
                                        <a href="javascript:void(0)" class="disabled_hover" id="Short-term">
                                            <label class="radio-inline">
                                                <input type="radio" name="optionsRadiosinline" id="optionsRadios4" value="option2">短期停车
                                            </label>
                                        </a>
                                    </div>
                                    <div class="article select-menu" id="add_Card" style="display: none">
                                        <h4 class="parking-select-title">添加卡：</h4>
                                        <div href="javascript:void(0)" class="line_indent">
                                            <div class="add_card">
                                                <span>卡类别：</span>
                                                <select name="type" id="select_card" onchange="select_card(this.options[this.options.selectedIndex].value, this.options[this.options.selectedIndex].innerHTML)">
                                                    <option value="-1">选择卡类别</option>
                                                    <c:forEach items="${shortParkTypeList}" var="item" varStatus="st">
                                                        <option value="${item.cardType}">${item.cardTypeDesc}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="add_card">
                                                <span>长/短期方案共存：</span>
                                                <select name="type" id="select_scheme" onchange="select_scheme(this.options[this.options.selectedIndex].value)">
                                                    <option value="-1">选择优先级</option>
                                                    <c:forEach items="${schemeList}" var="item" varStatus="st">
                                                        <option value="${item.code}">${item.desc}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="add_card">
                                                <span>起止时间：</span>
                                                <%--<input class="input_text_length" type="text" readonly="readonly" id="selectjetime_addcard" placeholder="起止时间">--%>
                                                <input type="text" readonly style="" placeholder="选择停车时间"
                                                       name="s_startTime" id="selectjetime_addcard" class="form-control" value=""/>
                                            </div>
                                            <div class="add_card">
                                                <span>时长：</span>
                                                <input class="parktext-config" id="total_time" value="" type="text" maxlength="2" oninput="this.value=this.value.replace(/\D/g,'').replace(/^0+(?=\d)/,'')" placeholder="时长/天">
                                            </div>
                                            <div class="add_card">
                                                <span>
                                                    <button type="button" class="btnstyle btn btn-primary btn-sm" onclick="addCard()">新增</button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="table-responsive">
                                        <table class="table table-bordered" id="longterm_park">
                                            <thead>
                                                <tr>
                                                    <th style="width: 10%">类别</th>
                                                    <th>时长</th>
                                                    <th>操作</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            <input type="hidden" value="${fn:length(parkConfigDataList)}" id="listsize">
                                                <c:forEach items="${parkConfigDataList}" var="item" varStatus="st">
                                                    <c:if test="${item.parkType == 0}">
                                                    <tr id="currentNumber_${item.id}">
                                                        <td>${item.cardTypeDesc}</td>
                                                        <td>
                                                            <input class="parktext-config" value="${item.totalTime}" type="text" oninput="this.value=this.value.replace(/\D/g,'').replace(/^0+(?=\d)/,'')" placeholder="时长">
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btnstyle btn btn-danger btn-xs" onclick="delete_btn(this, ${item.id})">删除</button>
                                                        </td>
                                                    </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                        <table class="table table-bordered" id="shortterm_park" style="display: none">
                                            <thead>
                                            <tr>
                                                <th style="width: 10%">类别</th>
                                                <th>时长</th>
                                                <th>起止时间</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>
                                            <tbody id="addCard_data">
                                            <c:forEach items="${parkConfigDataList}" var="item" varStatus="st">
                                                <c:if test="${item.parkType == 1}">
                                                    <tr id="currentNumber_${item.id}">
                                                        <td>${item.cardTypeDesc}</td>
                                                        <td>
                                                            <input class="parktext-config" value="${item.totalTime}" type="text" oninput="this.value=this.value.replace(/\D/g,'').replace(/^0+(?=\d)/,'')" placeholder="时长/天">
                                                        </td>
                                                        <td>
                                                            <input type="text" value="${item.validStartTimeStr}" class="jeinput input_text_length" parkid="${item.id}" id="selectjetime${st.count-1}" readonly="readonly" placeholder="起止时间">
                                                        </td>
                                                        <td>
                                                            <button type="button" class="btnstyle btn btn-danger btn-xs" delete_url="${ctx}/parking/deleteRecord/" onclick="delete_btn(this, ${item.id})">删除</button>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 50%;margin-left: 25%;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               <%-- <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>--%>
            </div>
            <div class="modal-body text-center">是否删除当前类别卡？</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary" id="data_remove_ok_id" onclick="data_remove_ok(this)">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<script src="${ctx }/assets/js/parking/parking_index.js"></script>
<script src="${ctx }/assets/js/moment.js"></script>
<script src="${ctx }/assets/js/daterangepicker.js"></script>
<script src="${ctx }/assets/js/common/common.js"></script>
</body>
</html>

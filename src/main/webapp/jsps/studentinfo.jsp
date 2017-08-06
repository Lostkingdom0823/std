<%@ page import="java.util.List" %>
<%@ page import="com.biz.std.model.Student" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: King
  Date: 2017/8/2
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <meta name="description" content="Common form elements and layouts"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css"/>

    <!-- page specific plugin styles -->
    <link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css"/>
    <link rel="stylesheet" href="assets/css/chosen.min.css"/>
	<link rel="stylesheet" href="assets/css/jquery.gritter.min.css" />
	<link rel="stylesheet" href="assets/css/select2.min.css" />
    <link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css"/>
    <link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css"/>
    <link rel="stylesheet" href="assets/css/daterangepicker.min.css"/>
    <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css"/>
    <link rel="stylesheet" href="assets/css/bootstrap-colorpicker.min.css"/>

    <!-- text fonts -->
    <link rel="stylesheet" href="assets/css/fonts.googleapis.com.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />
    <script src="assets/js/ace-extra.min.js"></script>
    <title>student infomation management page</title>
</head>
<body class="no-skin">
<%!
    List<Student> students = new ArrayList<Student>();
    Integer contentPage = 1;
    Integer maxPage = 1;
    Integer count = 0;
    Integer totalDetails = 0;
%>

<div id="navbar" class="navbar navbar-default ace-save-state">
    <div class="navbar-container ace-save-state" id="navbar-container">
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <div class="navbar-header pull-left">
            <a href="index.html" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    Ace Admin
                </small>
            </a>
        </div>

        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav" style="height: auto">
                <li class="grey dropdown-modal">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-tasks"></i>
                        <span class="badge badge-grey">4</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-check"></i>
                            4 Tasks to complete
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar">
                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Software Update</span>
                                            <span class="pull-right">65%</span>
                                        </div>

                                        <div class="progress progress-mini">
                                            <div style="width:65%" class="progress-bar"></div>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Hardware Upgrade</span>
                                            <span class="pull-right">35%</span>
                                        </div>

                                        <div class="progress progress-mini">
                                            <div style="width:35%" class="progress-bar progress-bar-danger"></div>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Unit Testing</span>
                                            <span class="pull-right">15%</span>
                                        </div>

                                        <div class="progress progress-mini">
                                            <div style="width:15%" class="progress-bar progress-bar-warning"></div>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
                                            <span class="pull-left">Bug Fixes</span>
                                            <span class="pull-right">90%</span>
                                        </div>

                                        <div class="progress progress-mini progress-striped active">
                                            <div style="width:90%" class="progress-bar progress-bar-success"></div>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                See tasks with details
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="purple dropdown-modal">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                        <span class="badge badge-important">8</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-exclamation-triangle"></i>
                            8 Notifications
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar navbar-pink">
                                <li>
                                    <a href="#">
                                        <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
														New Comments
													</span>
                                            <span class="pull-right badge badge-info">+12</span>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <i class="btn btn-xs btn-primary fa fa-user"></i>
                                        Bob just signed up as an editor ...
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
														New Orders
													</span>
                                            <span class="pull-right badge badge-success">+8</span>
                                        </div>
                                    </a>
                                </li>

                                <li>
                                    <a href="#">
                                        <div class="clearfix">
													<span class="pull-left">
														<i class="btn btn-xs no-hover btn-primary fa fa-twitter"></i>
														Followers
													</span>
                                            <span class="pull-right badge badge-info">+11</span>
                                        </div>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="#">
                                See all notifications
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="green dropdown-modal">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
                        <span class="badge badge-success">5</span>
                    </a>

                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="ace-icon fa fa-envelope-o"></i>
                            5 Messages
                        </li>

                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar">
                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="../student/assets/images/avatars/avatar.png" class="msg-photo"
                                             alt="Alex's Avatar"/>
                                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Alex:</span>
														Ciao sociis natoque penatibus et auctor ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>a moment ago</span>
													</span>
												</span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="../student/assets/images/avatars/avatar3.png" class="msg-photo"
                                             alt="Susan's Avatar"/>
                                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Susan:</span>
														Vestibulum id ligula porta felis euismod ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>20 minutes ago</span>
													</span>
												</span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="../student/assets/images/avatars/avatar4.png" class="msg-photo"
                                             alt="Bob's Avatar"/>
                                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Bob:</span>
														Nullam quis risus eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>3:15 pm</span>
													</span>
												</span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="../student/assets/images/avatars/avatar2.png" class="msg-photo"
                                             alt="Kate's Avatar"/>
                                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Kate:</span>
														Ciao sociis natoque eget urna mollis ornare ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>1:33 pm</span>
													</span>
												</span>
                                    </a>
                                </li>

                                <li>
                                    <a href="#" class="clearfix">
                                        <img src="../student/assets/images/avatars/avatar5.png" class="msg-photo"
                                             alt="Fred's Avatar"/>
                                        <span class="msg-body">
													<span class="msg-title">
														<span class="blue">Fred:</span>
														Vestibulum id penatibus et auctor  ...
													</span>

													<span class="msg-time">
														<i class="ace-icon fa fa-clock-o"></i>
														<span>10:09 am</span>
													</span>
												</span>
                                    </a>
                                </li>
                            </ul>
                        </li>

                        <li class="dropdown-footer">
                            <a href="inbox.html">
                                See all messages
                                <i class="ace-icon fa fa-arrow-right"></i>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="light-blue dropdown-modal">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img class="nav-user-photo" src="../student/assets/images/avatars/user.jpg" alt="Jason's Photo"/>
                        <span class="user-info">
									<small>Welcome,</small>
									Jason
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-cog"></i>
                                Settings
                            </a>
                        </li>

                        <li>
                            <a href="profile.html">
                                <i class="ace-icon fa fa-user"></i>
                                Profile
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="ace-icon fa fa-power-off"></i>
                                Logout
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div><!-- /.navbar-container -->
</div>
<div class="main-container ace-save-state" id="main-container">
    <div id="sidebar" class="sidebar responsive ace-save-state" data-sidebar="true" data-sidebar-scroll="true" data-sidebar-hover="true">
        <ul class="nav nav-list" style="top: 0px;">
            <li class="">
                <a href="#">
                    <i class="menu-icon fa fa-user-o"></i>
                    Students
                </a>
            </li>
            <li class="">
                <a href="#">
                    <i class="menu-icon fa fa-flag"></i>
                    Grade
                </a>
            </li>
            <li class="">
                <a href="#">
                    <i class="menu-icon fa fa-book"></i>
                    Subject
                </a>
            </li>
        </ul>
    </div>

    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs"></div>
            <div class="page-content" id="infoContent">
                <div class="page-header">
                    <h1>
                        Tables
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            Static &amp; Dynamic Tables
                        </small>
                    </h1>
                </div><!-- /.page-header -->
                <button class="btn btn-primary btn-lg right pull-right" id="newInfo">
                    <i class="ace-icon fa fa-pencil align-left bigger-110"></i>new info</button>
                <div class="row">
                    <div class="col-xs-12">
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <table id="simple-table" class="table  table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>queue</th>
                                        <th>student id</th>
                                        <th>student name</th>
                                        <th>student class</th>
                                        <th>student birthday</th>
                                        <th>student sex</th>
                                        <th>number of subjects</th>
                                        <th>avarage score</th>
                                        <th>optiions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        students.clear();
                                        count=0;
                                        if(request.getAttribute("students")!=null){
                                        students = (List<Student>) request.getAttribute("students");
                                        }
                                        students.size();
                                        maxPage = (Integer) request.getAttribute("maxPage");
                                        contentPage=(Integer) request.getAttribute("contentPage");
                                        totalDetails=(Integer) request.getAttribute("totalDetails");
                                        Iterator<Student> stuIterator = students.iterator();
                                        while(stuIterator.hasNext()){
                                            count++;
                                            Student student = stuIterator.next();
                                    %>
                                        <tr>
                                            <td><%=(contentPage-1)*10+count%></td>
                                            <td><%=student.getStudentId()==null ? "":student.getStudentId()%></td>
                                            <td><%=student.getStudentName()==null ? "":student.getStudentName()%></td>
                                            <td><%=student.getStudentClass()==null ? "":student.getStudentClass()%></td>
                                            <td><%=student.getStudentBirthday()==null ? "":student.getStudentBirthday().toString()%></td>
                                            <td><%=student.getStudentSex()==null ? "":student.getStudentSex()%></td>
                                            <td><%=student.getStudentSujectsInLearning()==null ? "":student.getStudentSujectsInLearning()%></td>
                                            <td><%=student.getStudentAvgScore()==null ? "":student.getStudentAvgScore()%></td>
                                            <td>
                                                <div class="hidden-sm hidden-xs btn-group">
                                                    <button class="btn btn-xs btn-info" >
                                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                    </button>

                                                    <button class="btn btn-xs btn-danger">
                                                        <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                    </button>
                                                </div>

                                                <div class="hidden-md hidden-lg">
                                                    <div class="inline pos-rel">
                                                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                                            </button>

                                                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                                                <li>
                                                                    <a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
                                                                                <span class="green">
                                                                                    <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                                                </span>
                                                                    </a>
                                                                </li>

                                                                <li>
                                                                    <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                                                <span class="red">
                                                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                                </span>
                                                                    </a>
                                                                </li>
                                                            </ul>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    <%
                                        }
                                    %>
                                    </tbody>
                                </table>
                                <div class="row">
                                    <div class="col-xs-6" style="margin-top: 8px">
                                        <div class="dataTables_info" id="dynamic-table_info" role="status" aria-live="polite">showing <%=(contentPage*10-9)%> to <%=(contentPage*10)%> of <%=totalDetails%></div>
                                    </div>
                                    <div class="col-xs-6">
                                        <div class="dataTables_paginate paging_simple_numbers" id="dynamic-table_paginate">
                                            <ul class="pagination">
                                                <li class="paginate_button ">
                                                    <a href="" value="pages">1</a>
                                                </li>
                                                <%
                                                    if(contentPage>=5){
                                                %>
                                                <p style="display:inline;margin:10px">…</p>
                                                <%
                                                    }
                                                    for(int i = contentPage-2 ; i <= contentPage+2; i++){
                                                        if(i<=1)
                                                            continue;
                                                        if(i>maxPage){
                                                            break;
                                                        }
                                                %>
                                                <li class="paginate_button ">
                                                    <a href="" value="pages"><%=i %></a>
                                                </li>
                                                <%
                                                    }
                                                    if((contentPage+2)<(maxPage-1)){
                                                %>
                                                <p style="display:inline;margin:10px">…</p>
                                                <%
                                                    }
                                                    if(contentPage+2<maxPage){
                                                %>
                                                <li class="paginate_button ">
                                                    <a href="" value="pages"><%= maxPage %></a>
                                                </li>
                                                <%
                                                    }
                                                %>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
			<div class="page-content" id="formContent" style="display:none">
				<div class="page-header">
                    <h1>
                        Form
                        <small>
                            <i class="ace-icon fa fa-angle-double-right"></i>
                            Static &amp; Dynamic Tables
                        </small>
                    </h1>
                </div><!-- /.page-header -->
				<div class="row">
					<form class="form-horizontal" id="form" role="form" action="http://localhost:8585/std/student/insert.do" method="post">
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="studentId"> Student id </label>

							<div class="col-sm-9">
								<input type="text" id="studentId" name="studentId" placeholder="Student id" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="studentName"> Student name </label>

							<div class="col-sm-9">
								<input type="text" id="studentName" name="studentName" placeholder="Student name" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="studentClass"> Student class </label>
                            <span class="col-sm-1 label label-xlg label-white middle " style="margin-right:10px;margin-left:10px;margin-top:4px">Grade</span>
                            <div class="col-sm-1 no-padding-left">
                                <select class="form-control " id="grade">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                </select>
                            </div>
                            <span class="col-sm-1 label label-xlg label-white middle " style="margin-right:10px;margin-top:4px">Class</span>
                            <div class="col-sm-1 no-padding-left">
                                <select class="form-control " id="class">
                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                </select>
							</div>
                            <input type="hidden" name="studentClass" id="studentClass"/>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="studentBirthday"> Student birthday </label>

							<div class="col-sm-9">
								<div class="input-medium">
									<div class="input-group">
										<input class="input-medium date-picker form-control" name="studentBirthday" id="studentBirthday" type="text" data-date-format="yyyy-mm-dd" placeholder="yyyy-mm-dd" />
										<span class="input-group-addon">
											<i class="ace-icon fa fa-calendar"></i>
										</span>
									</div>
								</div>
							</div>

						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="studentSex"> Student gender </label>
							<!-- !!! -->
							<div class="col-sm-9">
								<label class="inline">
									<input id="genderMale" name="studentSex" type="radio" class="ace" value="Male" />
									<span class="lbl middle"> Male</span>
								</label>

								&nbsp; &nbsp; &nbsp;
								<label class="inline">
									<input id="genderFemale" name="studentSex" type="radio" class="ace" value="Female"/>
									<span class="lbl middle"> Female</span>
								</label>
							</div>

						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label no-padding-right" for="studentAvgScore"> Student avarage score </label>

							<div class="col-sm-9">
								<input type="text" id="studentAvgScore" name="studentAvgScore" placeholder="0.0" class="col-xs-10 col-sm-5" />
							</div>
						</div>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
								<button class="btn btn-primary" type="submit" id="submit">
									<i class="ace-icon fa fa-check bigger-110"></i>
									Submit
								</button>

								&nbsp; &nbsp; &nbsp;
								<button class="btn" type="reset">
									<i class="ace-icon fa fa-undo bigger-110"></i>
									Reset
								</button>
								
								&nbsp; &nbsp; &nbsp;
								<button class="btn btn-warning" type="reset" id="return">
									<i class="ace-icon fa fa-arrow-left bigger-110"></i>
									Back
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
    </div>
</div>
<script src="assets/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="assets/js/bootstrap.min.js"></script>

<!-- page specific plugin scripts -->
<script src="assets/js/jquery-ui.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/chosen.jquery.min.js"></script>
<script src="assets/js/spinbox.min.js"></script>
<script src="assets/js/bootstrap-datepicker.min.js"></script>
<script src="assets/js/bootstrap-timepicker.min.js"></script>
<script src="assets/js/moment.min.js"></script>
<script src="assets/js/daterangepicker.min.js"></script>
<script src="assets/js/bootstrap-datetimepicker.min.js"></script>
<script src="assets/js/bootstrap-colorpicker.min.js"></script>
<script src="assets/js/jquery.knob.min.js"></script>
<script src="assets/js/autosize.min.js"></script>
<script src="assets/js/jquery.inputlimiter.min.js"></script>
<script src="assets/js/jquery.maskedinput.min.js"></script>
<script src="assets/js/bootstrap-tag.min.js"></script>

<!-- ace scripts -->
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        $("#newInfo").click(function(){
            $("#infoContent").hide();
            $("#formContent").show();
            $("#form").attr("action","http://localhost:8585/std/student/insert.do");
        });

        $("#return").click(function(){
            $("#infoContent").show();
            $("#formContent").hide();
        });

        $(".btn-info").click(function(){
            var $student = $(this).parent().parent().parent().find('td');
            $("#infoContent").hide();
            $("#formContent").show();
            $("#studentId").val($student.eq(1).html());
            $("#studentId").attr("readonly","readonly");
            $("#studentName").val($student.eq(2).html());
            $("#studentClass").val($student.eq(3).html());
            $("#studentBirthday").val($student.eq(4).html());
            $student.eq(5).html()=="Male"?$("#genderMale").prop("checked",true):$("#genderFemale").prop("checked",true);
            $("#studentAvgScore").val($student.eq(7).html());
            $("#form").attr("action","http://localhost:8585/std/student/update.do");
        });

        $(".btn-danger").click(function () {
            var $student = $(this).parent().parent().parent().find('td');
            if(confirm("是否删除该数据？")){
                window.location.href="http://localhost:8585/std/student/delete.do?studentId="+$student.eq(1).html();
            }
        });

        $("a[value$='pages']").click(function(){
            var $contentPage = $(this).html();
            var timeStamp=new Date().getTime();
            var url = "http://localhost:8585/std/student/getinfo.do?contentPage="+$contentPage+"&timestamp="+timeStamp;
            $(this).attr("href",url);
        });

        $("#submit").click(function () {
            var $className="Grade "+$("#grade").val()+" Class "+$("#class").val();
            $("#studentClass").val($className.toString());
            submit();
        });
    });



	jQuery(function($) {
	$('.input-daterange').datepicker({autoclose:true,format: 'yyyy-mm-dd'});  
	$('.date-picker').datepicker({
			autoclose: true,
			todayHighlight: true
		})
		//show datepicker when clicking on the icon
		.next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
	});
</script>
</body>
</html>

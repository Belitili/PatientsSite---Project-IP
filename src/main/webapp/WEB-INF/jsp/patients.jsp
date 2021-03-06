<%-- 
    Document   : patients
    Created on : Jul 17, 2017, 4:10:54 AM
    Author     : Marie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="domain.model.Patient"%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Overview - Patient DB" />
    </jsp:include>
    <body>
        <div id="container">
            <%@include file="header.jsp" %>
            <div id="screen" class="panel panel-default">
                <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
                <div id="content" class="panel-body">
                    <h1>Patient Overview</h1>
                    <% ArrayList<Patient> patients = (ArrayList<Patient>) request.getAttribute("patients");%>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Age</th>
                                    <th>Address</th>
                                    <th>Weight</th>
                                    <th>Height</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Patient patient : patients) {%>
                                <tr>
                                    <td class="patientID"><%= patient.getId()%></td>
                                    <td class="patientName"><%= patient.getFirstName()%> <%= patient.getLastName()%></td>
                                    <td class="patientAge"><%= patient.getAge()%></td>
                                    <td class="patientAddress"><%= patient.getAddress().getStreet() != null ? patient.getAddress() : "none"%></td>
                                    <td class="patientWeight"><%= patient.getWeightInKg()%></td>
                                    <td class="patientHeight"><%= patient.getHeightInCm()%></td>
                                    <td><a href="/ip-project/editPatient/<%=patient.getId()%>.htm">edit</a></td>
                                    <td><a href="/ip-project/requestDeletePatient/<%=patient.getId()%>.htm">delete</a></td>
                                </tr>
                                <% }
                                %>
                            </tbody>
                        </table>
                    </div>  
                    <a href="form.htm" class="btn btn-primary">Add Patient</a> 
                </div>
                <%@include file="footer.jsp" %>
            </div>
        </div>
    </body>
</html>

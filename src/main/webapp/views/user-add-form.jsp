<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Form</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
        }

        .form-title {
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"], input[type="email"], input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .dob-group {
            display: flex;
            gap: 10px;
        }

        select {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            width: 33.33%;
            box-sizing: border-box;
        }

        .gender-group {
            margin-bottom: 15px;
        }

        .gender-group input[type="radio"] {
            margin-right: 5px;
        }

        .gender-group label {
            margin-right: 15px;
            font-weight: normal;
        }

        button {
            background-color: #007bff;
            color: #fff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 48%;
        }

        button[type="reset"] {
            background-color: #ccc;
            margin-left: 4%;
        }

        button:hover {
            opacity: 0.9;
        }

        .button-group {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
<a href="${pageContext.request.contextPath}/users">List user</a>
<form action="http://localhost:8080/LTWWW_JAVA_11092025_TUAN04_DangNguyenTienPhat/users?action=insert" name="form-dk" method="post">
    <c:if test="${errors != null}">
        <p style="text-align: left; color:red;">
            <c:out value="${errors}" escapeXml="false"></c:out>
        </p>
    </c:if>
    <div class="form-container">
        <div class="form-title">User Registration Form</div>
        <c:if test="${not empty errors}">
            <div style="color: red;">
                    ${errors}
            </div>
        </c:if>
        <div class="form-group">
            <label for="firstName">First Name</label>
            <input type="text" id="firstName" name="txtFName" maxlength="30" required>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <input type="text" id="lastName" name="txtLName" maxlength="30" required>
        </div>
        <div class="form-group">
            <label for="email">Your Email</label>
            <input type="email" id="email" name="txtEmail" maxlength="30" required>
        </div>
        <div class="form-group">
            <label for="reEmail">Re-enter Email</label>
            <input type="email" id="reEmail" name="txtReEmail" maxlength="30" required>
        </div>
        <div class="form-group">
            <label for="password">New Password</label>
            <input type="password" id="password" name="txtPassword" maxlength="30" required>
        </div>
        <div class="form-group">
            <label>Date of Birth</label>
            <div class="dob-group">
                <select name="month" required>
                    <option value="">Month</option>
                    <option value="1">January</option>
                    <option value="2">February</option>
                    <option value="3">March</option>
                    <option value="4">April</option>
                    <option value="5">May</option>
                    <option value="6">June</option>
                    <option value="7">July</option>
                    <option value="8">August</option>
                    <option value="9">September</option>
                    <option value="10">October</option>
                    <option value="11">November</option>
                    <option value="12">December</option>
                    <!-- Các tháng khác -->
                </select>
                <select name="day" required>
                    <option value="">Day</option>
                    <% for (int i = 1; i <= 31; i++) { %>
                    <option value="<%= String.format("%02d", i) %>"><%= i %></option>
                    <% } %>
                </select>
                <select name="year" required>
                    <option value="">Year</option>
                    <% int currentYear = java.time.Year.now().getValue();
                        for (int i = currentYear; i >= currentYear - 100; i--) { %>
                    <option value="<%= i %>"><%= i %></option>
                    <% } %>
                </select>
            </div>
        </div>
        <div class="form-group gender-group">
            <label>Gender</label>
            <input type="radio" id="male" name="gender" value="male" required>
            <label for="male">Male</label>
            <input type="radio" id="female" name="gender" value="female" required>
            <label for="female">Female</label>
        </div>
        <div class="button-group">
            <button type="submit">Sign Up</button>
            <button type="reset">Reset</button>
        </div>
    </div>
</form>
</body>
</html>
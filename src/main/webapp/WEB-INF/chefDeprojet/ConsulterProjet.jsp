<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Service" %>
<%@ page import="Models.Projet" %>
<%@ page import="Models.Taches" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style>
        body {
            margin: auto;
            color: #ffffff;
            font: 600 16px/18px 'Open Sans', sans-serif;
            position: relative;
            padding: 20px;
        }

        .background-container {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: url(prj.jpeg) center/cover;
            filter: blur(3px);
            z-index: -1;
        }

        .overlay {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: 1;
        }

        .container {
            text-align: center;
            margin-bottom: 10px;
            position: relative;
            margin-top: 20px;
            z-index: 2;
        }

        .custom-form {
            text-align: center;
            margin-top: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .custom-form label {
            display: block;
            margin-bottom: 5px;
        }

        .custom-form input[type="text"] {
            width: 50%; /* Adjust the width as needed */
            padding: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            background: rgba(255, 255, 255, 0.3);
            color: white;
            font-size: 18px;
        }

        .custom-form button {
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border: none;
            color: white;
            padding: 10px 20px;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
        }

        .custom-form button:hover {
            background: rgba(40, 57, 101, .8);
        }

        .service-list-container {
            text-align: center;
            margin: 20px auto;
            width: 60%; /* Adjust the width as needed */
        }

        .task-list-container {
            text-align: center;
            margin: 20px auto;
            width: 60%; /* Adjust the width as needed */
        }

        .service-list {
            list-style: none;
            padding: 0;
        }

        .service-item {
            cursor: pointer;
            margin: 10px;
            padding: 10px;
            border: 1px solid #ffffff;
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border-radius: 5px;
        }

        .service-item a {
            color: #ffffff;
            text-decoration: none;
        }

        .service-item a:hover {
            text-decoration: underline;
        }

        .task-list {
            list-style: none;
            padding: 0;
        }

        .task-item {
            margin: 10px;
            padding: 10px;
            border: 1px solid #ffffff;
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border-radius: 5px;
        }

        .task-item label {
            color: #ffffff;
            display: block;
            margin-bottom: 5px;
        }

        .task-item input[type="text"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f8f8f8;
            color: #333;
        }

        .task-item button {
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 18px;
            cursor: pointer;
            border-radius: 5px;
        }

        .task-item button:hover {
            background: rgba(40, 57, 101, .8);
        }

        .button {
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
            background: rgba(40, 57, 101, .6);
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 25px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 5px;
        }

        .avancement-container {
            display: inline-block;
            vertical-align: top;
            margin-right: 10px; /* Adjust the margin as needed */
        }

        .avancement-container label {
            display: inline-block;
            margin-right: 5px; /* Adjust the margin as needed */
        }

        .avancement-container input {
            display: inline-block;
        }

        .toast {
            background-color: #4CAF50;
            color: white;
            padding: 16px;
            position: fixed;
            bottom: 15px;
            right: 15px;
            z-index: 1;
            border-radius: 5px;
            display: none; /* Initially hide the toast */
        }
 .success-message {
        background-color: #4CAF50; /* Couleur de fond verte */
        color: #fff; /* Couleur du texte blanc */
        padding: 15px; /* Marge intérieure pour l'apparence */
        margin-bottom: 20px; /* Marge inférieure pour l'espacement avec le contenu suivant */
        border-radius: 5px; /* Coins arrondis */
    }
    .error-message{
        background-color: red; /* Couleur de fond verte */
        color: #fff; /* Couleur du texte blanc */
        padding: 15px; /* Marge intérieure pour l'apparence */
        margin-bottom: 20px; /* Marge inférieure pour l'espacement avec le contenu suivant */
        border-radius: 5px; /* Coins arrondis */
    }
    </style>
    <title>Mes Tâches</title>
</head>
<body>




<div class="background-container"></div>
<div class="overlay"></div>
<div class="container">
<%-- Afficher la chaîne HTML du message de succès --%>
<%-- Afficher la chaîne HTML du message de succès --%>


<% String successMessage = (String) request.getAttribute("successMessage"); %>
    <% if (Objects.nonNull(successMessage) && !successMessage.isEmpty()) { %>
        <div class="success-message">
            <%= successMessage %>
        </div>
    <% } %>
  <% String errorHtml = (String) request.getAttribute("errorHtml"); %>
    <% if (Objects.nonNull(errorHtml) && !errorHtml.isEmpty()) { %>
        <div class="error-message">
            <%= errorHtml %>
        </div>
    <% } %>

    <form action="RechercheprojetChef" method="post" class="custom-form">
        <div class="form-group">
            <label for="searchProject">Rechercher un projet :</label>
            <input type="text" id="searchProject" name="projectName" required>
        </div>
        <div class="form-group">
            <button onclick="retour()" class="button">Retour</button>
            <button type="submit" class="button">Rechercher</button>
        </div>
    </form>

  
</div>
<script>
    function retour() {
        // Retourne à la page précédente dans l'historique du navigateur
        window.history.back();
    }
</script> 

</body>
</html>

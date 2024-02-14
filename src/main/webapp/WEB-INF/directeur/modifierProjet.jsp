<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Models.User"%>
<%@ page import="Models.Projet"%>
<%@ page import="Models.ChefDeProjet"%>
<%@ page import="java.util.List"%>
<%@ page import="Servlet.UserList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%! SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd"); %>
<%
    // Utiliser le service pour obtenir la liste des chefs de projet
    UserList userService = new UserList();
    List<User> chefsDeProjet = userService.getChefsDeProjet();
%>
<%
Projet projet = (Projet) request.getAttribute("selectedProjectId");
String nomProjet = "";

String descriptionProjet="";
String clientProjet="";
String dateDebut="";

String dateLivraison="";

ChefDeProjet chefDeprojet=new ChefDeProjet() ;
long id=0;
int joursDeveloppement=0;
    // Vérifiez si le projet existe avant de préremplir les champs
    if (projet != null) {
    	id=projet.getId();
        nomProjet = projet.getNomProjet();
         descriptionProjet = projet.getDescription();
         clientProjet = projet.getClient();
         dateDebut = dateFormatter.format(projet.getDateDebut());
         dateLivraison = dateFormatter.format(projet.getDateLivraison());
         joursDeveloppement = projet.getJoursDeveloppement();
        
         
         
        // Assurez-vous d'adapter cela en fonction de votre modèle User
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modifier Projet</title>
    <style>
        body {
            margin: auto;
            color: #ffffff;
            font: 600 16px/18px 'Open Sans', sans-serif;
            position: relative;
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

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            max-width: 500px;
            margin: auto;
        }

        label {
            margin-top: 10px;
            font-size: 18px;
            color: #ffffff;
            text-align: left;
            width: 100%;
        }

        input,
        select {
            width: 100%;
            padding: 10px;
            margin: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            background: rgba(255, 255, 255, 0.3);
            color: #ffffff;
        }

        input::placeholder,
        select::placeholder {
            color: #ffffff;
        }

        button {
            font-size: 20px;
            padding: 5px 10px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background: rgba(40, 57, 101, 0.6);
            margin-top: 5px;
            width: 70%;
            height: 30%;
        }

        button.cancel {
            background: rgba(40, 57, 101, 0.6);
        }

        button.delete {
            width: 70%;
        }

        .error-message {
            color: red;
            margin-top: 2px;
        }

        .confirmation-message {
            display: none;
            background-color: rgba(40, 57, 101, 0.6);
            color: #ffffff;
            border: 1px solid #3498db;
            border-radius: 5px;
            padding: 10px;
            margin-top: 10px;
        }

        .confirmation-message button {
            margin-right: 10px;
            background-color: #3498db;
            color: #ffffff;
            border: none;
            border-radius: 5px;
            padding: 5px 10px;
            cursor: pointer;
        }
         .confirmation-overlay {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
            justify-content: center;
            align-items: center;
            z-index: 3; /* Réglez la superposition au-dessus de vos autres éléments */
        }

        .confirmation-message {
            background-color: rgba(40, 57, 101, 0.6);
            color: #ffffff;
            border: 1px solid #3498db;
            border-radius: 5px;
            padding: 10px;
            margin-top: 10px;
            z-index: 4; /* Réglez la boîte de confirmation au-dessus de la superposition */
        }
    </style>
 <script>
    function confirmAction(action) {
        document.getElementById("confirmationMessage").style.display = "block";
        document.getElementById("confirmationText").innerHTML = "Voulez-vous vraiment " + action + " ce projet?";
        document.getElementById("confirmationButton").innerHTML = action;
        document.getElementById("actionField").value = action;
        document.querySelector('.confirmation-overlay').style.display = 'flex';
    }

    function proceedAction() {
        document.forms[0].submit();
    }

    function cancelAction() {
        alert("La fonction cancelAction() est appelée!");
        document.getElementById("confirmationMessage").style.display = "none";
        document.querySelector('.confirmation-overlay').style.display = 'none';
    }

</script>

</head>

<body>
<% String errorMessage = (String)session.getAttribute("Erreur lors du modification du projet"); %> 
<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
        <div style="color: red;">
            <%= errorMessage %>
        </div>
    <% } %>
    <div class="background-container"></div>
    <div class="overlay"></div>
    <div class="container">
        <form action="GestionmodificationServlet" method="post" onsubmit="return validateAndCalculate();">
            <div id="errorContainer" class="error-message" style="margin-top: 5px;"></div>

<input type="hidden" id="id" name="id" value="<%= id %>">
<input type="hidden" id="ancienNomProjet" name="ancienNomProjet" value="<%= nomProjet %>">
<input type="hidden" id="actionField" name="actionField" value="">

            <label for="nomProjet">Nom du Projet:</label>
            <input type="text" id="nomProjet" name="nomProjet" value="<%= nomProjet %>" required placeholder="Nom du Projet">

            <label for="descriptionProjet">Description du Projet:</label>
            <input type="text" id="descriptionProjet" name="descriptionProjet" required placeholder="Description du Projet" value="<%= descriptionProjet %>">

            <label for="clientProjet">Client du Projet:</label>
            <input type="text" id="clientProjet" name="clientProjet" required placeholder="Client du Projet" value="<%= clientProjet %>">

            <label for="dateDebut">Date de Début:</label>
            <input type="date" id="dateDebut" name="dateDebut" required placeholder="Date de Début" oninput="updateDays()" value="<%= dateDebut %>">

            <label for="dateLivraison">Date de Livraison:</label>
            <input type="date" id="dateLivraison" name="dateLivraison" required placeholder="Date de Livraison" oninput="updateDays()" value="<%= dateLivraison %>">

            <label for="joursDeveloppement">Nombre de Jours de Développement:</label>
            <input type="number" id="joursDeveloppement" name="joursDeveloppement" required value="<%= joursDeveloppement %>" placeholder="Nombre de Jours de Développement" readonly>

            <label for="chefProjet">Chef de Projet:</label>
            <select id="chefProjet" name="chefProjet" required>
                <%
                    for (User chef : chefsDeProjet) {
                %>
                    <option value="<%= chef.getEmail() %>" style="color: black;"><%= chef.getEmail() %></option>
                <%
                    }
                %>
            </select>

            <div style="display: flex; justify-content: space-between; margin-top: 20px;">
                <button type="button" onclick="goBack()" class="cancel" style="margin-right: 40px;">Annuler</button>
                <button type="button" id="modifyButton" onclick="confirmAction('Modifier')" style="margin-right: 40px;">Modifier</button>
                <button type="button" id="deleteButton" onclick="confirmAction('Supprimer')" class="cancel delete">Supprimer</button>
            </div>
            <div class="confirmation-overlay">
        <div id="confirmationMessage" class="confirmation-message">
            <p id="confirmationText"></p>
            <button id="confirmationButton" onclick="proceedAction()"></button>
            <button onclick="cancelAction()" class="cancel">Annuler</button>
        </div>
    </div>

            
        </form>
    </div>

    <script>
        function updateDays() {
            var startDate = new Date(document.getElementById("dateDebut").value);
            var endDate = new Date(document.getElementById("dateLivraison").value);
            var errorContainer = document.getElementById("errorContainer");
            var today = new Date();

            if (startDate < today || endDate < today) {
                errorContainer.innerHTML = "Les dates ne peuvent pas être antérieures à aujourd'hui.";
                return;
            }

            if (startDate > endDate) {
                errorContainer.innerHTML = "La date de livraison ne peut pas être antérieure à la date de début.";
                return;
            }

            var timeDiff = Math.abs(endDate.getTime() - startDate.getTime());
            var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24));

            document.getElementById("joursDeveloppement").value = diffDays;
            errorContainer.innerHTML = "";
        }
    </script>
</body>

</html>
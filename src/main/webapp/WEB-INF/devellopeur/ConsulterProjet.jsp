<%@ page import="java.util.List" %>
<%@ page import="Models.Service" %>
<%@ page import="Models.Projet" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste des Projets</title>
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

        .project-list {
            list-style: none;
            padding: 0;
        }

		.project-container {
        text-align: left;
        max-width: 400px; /* Set a maximum width for better readability, adjust as needed */
        margin: 0 auto; /* Center the content within the available space */
    }

    .project-details {
        text-align: left;
    }
        .project-item {
            cursor: pointer;
            margin: 10px;
            padding: 10px;
            border: 1px solid #ffffff;
            box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
		background: rgba(40, 57, 101, .6);
            border-radius: 5px;
            text-align: left; /* Align text to the left within each item */
        margin-bottom: 20px;
        }
        

        .service-list {
            list-style: none;
            padding: 0;
            display: none;
        }

        .service-item {
            margin: 5px;
            padding: 5px;
            border: 1px solid #ffffff;
             box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
		background: rgba(40, 57, 101, .6);
            border-radius: 3px;
        }
         .button {
     box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
		background: rgba(40, 57, 101, .6);
    border: none; /* Remove borders */
    color: white; /* White text */
    padding: 15px 32px; /* Padding */
    text-align: center; /* Center text */
    text-decoration: none; /* Remove underline */
    display: inline-block; /* Make it an inline-block element */
    font-size:25px; 
    margin: 4px 2px; /* Add some margin */
    cursor: pointer; /* Add cursor pointer on hover */
    border-radius: 5px; /* Optional: Add rounded corners */
}
  .project-item strong,
    .project-item div,
    .service-item strong {
        margin-bottom: 8px; /* Adjust the margin as needed */
    }
        
    </style>
</head>
<body>
<%-- Afficher le message de succès --%>
   
    <div class="background-container"></div>
    <div class="overlay"></div>
    <div class="container">
        <h2>Liste des Projets</h2>
        <ul class="project-list">
            <% for (Service service : (List<Service>)request.getAttribute("projets")) { %>
                <li class="project-item" onclick="showDetails('<%= service.getProjet().getId() %>')">
                   <div class="project-container">
                   <div class="project-details">
                      <label style="color:#D2B48C;  ">Projet:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; 
                  <%= service.getProjet().getNomProjet() %><br>
                  <label style="color:#D2B48C;  ">Client:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; 
                  <%= service.getProjet().getClient() %><br>
                   <label style="color: #D2B48C;  "> Description:</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label style=" display: block; text-align: center;"><%= service.getProjet().getDescription() %></label><br>
                   <label style="color: #D2B48C;  "> Date de début:</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  <%= service.getProjet().getDateDebut() %><br>
                   <label style="color:#D2B48C;  "> Date de livraison: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <%= service.getProjet().getDateLivraison() %><br>
                   <label style="color:#D2B48C;  "> Jours de developpement: </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= service.getProjet().getJoursDeveloppement() %><br>
                   <label style="color: #D2B48C;  "> methodologie : </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  <%= service.getProjet().getMethodologie() %><br>
                    <br>
<strong style="color: #87CEFA; display: block; text-align: center;">Les Informations Du Chef De Projet :</strong><br>

                      <label style="color: #D2B48C;  ">   Nom:  </label>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp; <%= service.getProjet().getChefDeProjet().getNom()%><br>
                       <label style="color: #D2B48C;  ">  Prenom: </label> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%= service.getProjet().getChefDeProjet().getPrenom()%><br>
                    <!-- Ajouter d'autres champs selon votre modèle -->
                    <!-- Afficher les détails du service associé -->
                    <div class="service-list" id="serviceList<%= service.getProjet().getId() %>">
                        <div class="service-item">
                            <strong>Service Détails:</strong><br>
                            Durée: &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; <%= service.getDuree() %> jours<br>
                            Description:&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;  <%= service.getDescription() %>
                        </div>
                        <!-- Ajouter d'autres champs selon votre modèle -->
                    </div>
                    </div>
                    </div>
                </li>
            <% } %>
        </ul> <button onclick="retour()" class="button">Retour</button>
    </div>
    

    <script>
        function showDetails(projetId) {
            var serviceList = document.getElementById("serviceList" + projetId);
            serviceList.style.display = (serviceList.style.display === "none" || serviceList.style.display === "") ? "block" : "none";
        }  function retour() {
        // Retourne à la page précédente dans l'historique du navigateur
        window.history.back();
    }
    </script>





  

</body>
</html>

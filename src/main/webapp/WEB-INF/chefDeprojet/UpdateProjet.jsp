<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page import="Models.Projet"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
    .superposition {
    display: none;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
     cursor: pointer; 
    background-color: rgba(0, 0, 0, 0.75); /* Fond gris translucide */
}

.modal-content {
    box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .5), 0 17px 50px 0 rgba(0, 0, 0, .3);
    background: rgba(20, 37, 81, .6);
    margin: 15% auto;
    border-radius: 15px;
    padding: 20px;
    border: 1px solid #888;
    width: 70%;
   cursor: pointer; 
}

.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}
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

        .cv-container {
           box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
		background: rgba(40, 57, 101, .6);
            padding: 20px;
            border-radius: 10px;
            width: 70%;
            margin: 0 auto;
        }

        .profile-image {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
        }

        .profile-info {
            margin-top: 20px;
            text-align: left;
        }

        .section {
            margin-top: 30px;
        }

        .section h3 {
            border-bottom: 2px solid #ffffff;
            padding-bottom: 5px;
        }

        .section p {
            margin-top: 10px;
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

.checkbox-group {
    display: flex;
    flex-wrap: wrap;
    margin-bottom: 10px;
}

.checkbox-item {
    flex: 1 0 25%; /* 25% pour afficher jusqu'à 4 éléments par ligne */
    padding: 5px;
    box-sizing: border-box;
}
/* Styles pour le toast */
.toast {
    position: fixed;
    top: 20px;
    right: 20px;
    background-color: #4CAF50;
    color: white;
    padding: 16px;
    border-radius: 8px;
    z-index: 1;
    display: none;
}


.toast.show {
    animation: fadeInOut 3s;
}

@keyframes fadeInOut {
    0%, 100% {
        opacity: 0;
    }
    10%, 90% {
        opacity: 1;
    }
    
}

 .section ol {
        list-style: none;
        padding: 0;
        text-align: center; /* Center the list items */
    }

    .section ol li{
        display: inline-block;
        margin: 0 10px; /* Adjust the margin as needed */
    }

/* Ajoutez votre style existant ici */
 .modal-content {
 		margin-top: 50px;
        padding: 20px;
    }

    .section {
        margin-bottom: 20px;
    }

    .checkbox-group,
    .radio-group,
    .date-grou {
        border-bottom: 1px solid #ccc;
        padding-bottom: 10px;
        margin-bottom: 10px;
    }

    .checkbox-item,
    .radio-item {
        margin-bottom: 5px;
    }

    label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    </style>
    <title>Projet</title>
</head>
<body>
 <% String successMessage = (String)session.getAttribute("successMessage"); %>
    <% if (Objects.nonNull(successMessage)) { %>
        <div class="toast">
            <p><%= successMessage %></p>
        </div>
        <% session.removeAttribute("successMessage"); %>
    <% } %>


    <div class="background-container"></div>
    <div class="overlay"></div>
    <div class="container">
        <div class="cv-container">
         
            <div class="profile-info">
               
<%
Projet projet = (Projet) request.getAttribute("projet");
%>
<p>Nom Projet: <%= projet.getNomProjet() %></p>
<p>Client: <%= projet.getClient() %></p>
<p>Date début: <%= projet.getDateDebut() %></p>
<p>Date livraison: <%= projet.getDateLivraison() %></p>
<p>Nbre de jours de Developpement : <%= projet.getJoursDeveloppement() %></p>



                <!-- Add more profile information as needed -->
            </div>

         <div class="section">
                    <h3>Technologie</h3>
                    <ol >
                        <%-- Retrieve and display competencies using Java for loop --%>
                        <% List<String> technologie = (List<String>) request.getAttribute("technologie"); %>
                        <% if (technologie != null) { %>
                            <% for (String competency : technologie) { %>
                                <ol><%= competency %></ol>
                            <% } %>
                        <% } %>
                    </ol>
                </div>     
   

           
              

           
     <!-- ... Votre code existant ... -->

<!-- Nouvelle section pour les boutons -->


<!-- Superposition (modal) -->
<div id="superposition" class="superposition">
    <div class="modal-content">
        <span class="close" onclick="fermerSuperposition()">&times;</span>
        <h3 style="text-align: center;">Modifier</h3>
        <form action="ModifieProjet" method="post">
            
            <!-- Section Technologie -->
            <div class="section checkbox-group">
         <input type="hidden" name="projetId" value="<%= projet.getId() %>">

               <% List<Long> technologieId = (List<Long>) request.getAttribute("TechnologiId"); %>
                        <% if (technologieId != null) { %>
                            <% for (Long competicy : technologieId) { %>
                             <input type="hidden" name="technologieId" value="<%= competicy %>">
                                
                            <% } %>
                        <% } %>
                      
                <label>Technologie :</label>
                <div class="checkbox-item">
                    <input type="checkbox" name="technologie" value="Python"> Python
                </div>
                <div class="checkbox-item">
                    <input type="checkbox" name="technologie" value="React"> React
                </div>
                <div class="checkbox-item">
                    <input type="checkbox" name="technologie" value="Node.js"> Node.js
                </div>
                <div class="checkbox-item">
                    <input type="checkbox" name="technologie" value="Java"> Java
                </div>
                 <div class="checkbox-item">
                    <input type="checkbox" name="technologie" value="HTML"> HTML
                </div>
                <div class="checkbox-item">
                    <input type="checkbox" name="technologie" value="SQL"> SQL
                </div>
                <div class="checkbox-item">
                    <input type="checkbox" name="technologie" value="CSS"> CSS
                </div>
                <!-- Ajoutez d'autres technologies selon vos besoins -->
            </div>

            <!-- Section Méthodologie -->
            <div class="section radio-group">
                <label>Méthodologie :</label>
                <div class="radio-item">
                    <input type="radio" name="methodologie" value="Agile"> Agile
                </div>
                <div class="radio-item">
                    <input type="radio" name="methodologie" value="Scrum"> Scrum
                </div>
                <!-- Ajoutez d'autres méthodologies selon vos besoins -->
            </div>

            <!-- Section Date -->
            <div class="section date-group">
                <label>Date de Réunion :</label>
                <input type="date" name="date_reunion">
            </div>

            <!-- Bouton de validation -->
            <button type="submit" class="button" style="margin-top: 10px;">Valider</button>
            
        </form>
    </div>
</div>

<div class="section">
    <button onclick="retour()" class="button">Retour</button>
    <button onclick="afficherSuperposition()" class="button">Modifier</button>
</div>

<script>
function afficherSuperposition() {
    // Affiche la superposition
    var superposition = document.getElementById("superposition");
    superposition.style.display = "block";
}

function fermerSuperposition() {
    // Ferme la superposition
    var superposition = document.getElementById("superposition");
    superposition.style.display = "none";
}



    function retour() {
        // Retourne à la page précédente dans l'historique du navigateur
        window.history.back();
    }
</script>

<!-- ... Votre code existant ... -->

   
</body>
</html>

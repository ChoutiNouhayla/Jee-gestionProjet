<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <script>
        function deconnexion() {
        	window.open('http://localhost:8080/project/','_blank');
            // Exécuter le script pour fermer l'onglet actuel et ouvrir une nouvelle fenêtre/onglet
          
            window.close();
        }
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <style>
        body {
            margin: auto;
            color: black;
            font: 600 16px/18px 'Open Sans', sans-serif;
            position: relative;
        }

        body::before {
            content: "";
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: url(prj.jpeg);
            filter: blur(3px);
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
            margin-top: 100px;
            position: relative;
            z-index: 2;
        }

        h2 {
            color: #333;
            font-size: 30px;
        }

        .button-container {
            margin-top: 60px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .button-container a {
            margin-top: 20px;
        }

        button {
            font-size: 30px;
            padding: 20px 40px;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background: rgba(40, 57, 101, 0.6);
            width: 580px;
            height: 100px;
        }

        h2 {
            margin-top: 20px;
            color: #ffffff;
        }
    </style>
    
</head>
<body>
    <div class="overlay"></div>
    <div class="container">
        <h2>Bienvenue, Devellopeur!</h2>
        <div class="button-container">
            <a href="ProfileServlet">
                <button>Profile</button>
            </a>
            <a href="MesProjetServlets">
                <button>Mes Projets</button>
            </a>
            
             <a href="AfficherTache">
                <button>Mes taches</button>
            </a> <a >
               <button onclick="deconnexion()">Deconnexion</button>
            </a>
        </div>
    </div>
</body>
</html>

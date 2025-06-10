// Attend que le contenu de la page soit entièrement chargé avant d'exécuter le script
document.addEventListener('DOMContentLoaded', function() {
    
    // Sélectionne les éléments nécessaires par leur ID ou classe
    const sidebar = document.querySelector('.sidebar-left');
    const toggleButton = document.getElementById('sidebarToggle');

    // Vérifie que le bouton et la barre latérale existent bien
    if (toggleButton && sidebar) {
        
        // Ajoute un écouteur d'événement sur le clic du bouton
        toggleButton.addEventListener('click', function() {
            // Ajoute ou retire la classe 'collapsed' de la barre latérale
            sidebar.classList.toggle('collapsed');
        });
    }
});
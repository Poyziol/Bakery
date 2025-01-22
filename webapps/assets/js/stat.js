// Graphique des ventes
const salesCtx = document.getElementById('salesChart').getContext('2d');
new Chart(salesCtx, {
    type: 'line',
    data: {
        labels: ['Jan', 'Fév', 'Mar', 'Avr', 'Mai', 'Juin'],
        datasets: [{
            label: 'Ventes (€)',
            data: [12000, 15000, 14000, 18000, 20000, 22000],
            borderColor: '#d35400',
            tension: 0.4,
            fill: false
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: { display: false }
        }
    }
});

// Produits les plus vendus
const productsCtx = document.getElementById('topProductsChart').getContext('2d');
new Chart(productsCtx, {
    type: 'bar',
    data: {
        labels: ['Baguette', 'Croissant', 'Pain au chocolat', 'Tarte aux pommes'],
        datasets: [{
            label: 'Quantité Vendue',
            data: [500, 300, 200, 150],
            backgroundColor: ['#e67e22', '#d35400', '#e59866', '#f39c12']
        }]
    },
    options: {
        responsive: true,
        plugins: {
            legend: { display: false }
        }
    }
});

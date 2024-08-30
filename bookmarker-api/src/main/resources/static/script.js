const apiUrl = 'http://127.0.0.1:8090/api/bookmarks';

let currentPage = 1;
let totalPages = 1;

document.addEventListener('DOMContentLoaded', () => {
    loadPage(currentPage);

    document.getElementById('prev-btn').addEventListener('click', () => {
        if (currentPage > 1) {
            currentPage--;
            loadPage(currentPage);
        }
    });

    document.getElementById('next-btn').addEventListener('click', () => {
        if (currentPage < totalPages) {
            currentPage++;
            loadPage(currentPage);
        }
    });

    document.getElementById('create-bookmark-form').addEventListener('submit', (event) => {
        event.preventDefault();
        createBookmark();
    });

    document.getElementById('search-input').addEventListener('input', () => {
        currentPage = 1; // Reset to first page on search
        loadPage(currentPage);
    });
});

function loadPage(page) {
    const query = document.getElementById('search-input').value;
    const url = `${apiUrl}?query=${encodeURIComponent(query)}&page=${page}`;

    fetch(url)
        .then(response => response.json())
        .then(data => {
            totalPages = data.totalPages;
            updateTable(data.data);
            updatePagination(data);
        })
        .catch(error => console.error('Error fetching data:', error));
}

function updateTable(bookmarks) {
    const tableBody = document.querySelector('#bookmarks-table tbody');
    tableBody.innerHTML = '';

    bookmarks.forEach(bookmark => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${bookmark.id}</td>
            <td>${bookmark.title}</td>
            <td><a href="${bookmark.url}" target="_blank">${bookmark.url}</a></td>
            <td>${new Date(bookmark.createdAt).toLocaleString()}</td>
        `;
        tableBody.appendChild(row);
    });
}

function updatePagination(data) {
    document.getElementById('prev-btn').disabled = !data.hasPrevious;
    document.getElementById('next-btn').disabled = !data.hasNext;
    document.getElementById('page-info').textContent = `Page ${data.currentPage} of ${data.totalPages}`;
}

function createBookmark() {
    const title = document.getElementById('title').value;
    const url = document.getElementById('url').value;

    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title, url }),
    })
        .then(response => response.json())
        .then(() => {
            // Clear form fields
            document.getElementById('title').value = '';
            document.getElementById('url').value = '';

            // Reload the current page
            loadPage(currentPage);
        })
        .catch(error => console.error('Error creating bookmark:', error));
}

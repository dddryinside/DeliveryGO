function loadFragment(elementId, url) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Failed to load fragment: ${url}`);
            }
            return response.text();
        })
        .then(html => {
            document.getElementById(elementId).innerHTML = html;
        })
        .catch(error => console.error(error));
}

loadFragment('header', '/fragments/header.html');
loadFragment('footer', '/fragments/footer.html');
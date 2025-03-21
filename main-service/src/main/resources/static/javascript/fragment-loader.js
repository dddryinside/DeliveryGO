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

document.addEventListener("DOMContentLoaded", function () {
    loadFragment('header', '/fragments/header.html');
    loadFragment('footer', '/fragments/footer.html');
    loadFragment('slider-container', '/fragments/slider.html');

    const headContent = `
                <link rel="stylesheet" href="/styles.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
            `;
    const head = document.head;
    head.innerHTML += headContent;
});
function sendRequest(type, url) {
    fetch(url, {
        method: type,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                alert('Error!');
            }
        })
        .catch(error => console.error('Error:', error));
}
function getUrlParam (paramName) {
    const currentUrl = window.location.href;
    const url = new URL(currentUrl, window.location.origin);
    const params = new URLSearchParams(url.search);
    return params.get(paramName);
}
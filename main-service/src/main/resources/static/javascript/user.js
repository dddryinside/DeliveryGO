async function getUserFromContext() {
    try {
        const response = await fetch('/api/get-user-from-context');
        if (!response.ok) {
            throw new Error('Ошибка сети: ' + response.statusText);
        }
        return await response.json();
    } catch (error) {
        console.error('Не удалось получить пользователя:', error);
        return null;
    }
}
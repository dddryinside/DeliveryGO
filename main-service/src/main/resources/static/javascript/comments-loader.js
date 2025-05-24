function loadComments(commentFormPermissionUrl, commentsUrl) {
    getCommentFormPermission(commentFormPermissionUrl)
        .then(permission => {
            if (permission === true) {
                loadFragment("comment-form", "/fragments/comment-form.html")
                const commentForm = document.getElementById("comment-form");
                commentForm.style.display = 'block';
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });

    fetch(commentsUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(comments => {
            if (comments.length === 0) {
                const commentsSection = document.getElementById("comments-section");
                commentsSection.innerHTML = "<p>Комментариев пока нет :(</p>";
                return;
            }
            comments.forEach(comment => {
                addComment(comment);
            });
        })
        .catch(error => {
            console.error("Error loading info:", error);
        });
}

function getCommentFormPermission(permissionUrl) {
    return fetch(permissionUrl)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .catch(error => {
            console.error("Error loading info:", error);
            throw error;
        });
}

function addComment(comment) {
    const commentsSection = document.getElementById("comments-section");

    const commentBlock = document.createElement("div");
    commentBlock.classList.add("comment");

    const authorName = document.createElement("p");
    authorName.classList.add("author-name");

    const strong = document.createElement("strong");
    strong.textContent = comment.authorUsername;
    authorName.appendChild(strong);

    const commentText = document.createElement("p");
    commentText.classList.add("comment-text");
    commentText.textContent = comment.text;

    const commentDate = document.createElement("p");
    commentDate.classList.add("comment-date");
    commentDate.textContent = comment.dateTime;

    const deleteButton = document.createElement("a")
    deleteButton.textContent = "Удалить комментарий";
    deleteButton.addEventListener("click", () => {
        deleteComment(comment)
    });

    commentBlock.appendChild(authorName);
    commentBlock.appendChild(commentText);
    commentBlock.appendChild(commentDate);

    if (comment.deletePermission === true) {
        commentBlock.appendChild(deleteButton);
    }

    commentsSection.appendChild(commentBlock);
}

function sendComment() {
    const comment = {
        text: document.getElementById("text").value,
        holderId: courierId
    }

    fetch('/api/save-courier-comment', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(comment)
    }).then(() => {
        location.reload();
    });
}

function deleteComment(comment) {
    fetch(`/api/delete-courier-comment`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(comment)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete comment');
            }
        }).then(() => {
            location.reload();
        });
}
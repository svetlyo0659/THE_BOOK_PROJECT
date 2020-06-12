
$(function() {
  $('.navbar-brand').click(getBooks).click();
  $('#searchBooks').click(function() {
    getBooks($('input').first().val());
    return false;
  });
  $('#changePasswordButton').click(changePassword);
  $('#bookViewForm').submit(submitComment);

  function submitComment() {
    var bookId = $('#reviewBookId').val();
    fetch(`/api/v1/books/${bookId}/comments?userName=admin`, {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          content: $('#reviewText').val(),
        })
      }).then(function(response) {
        if (response.status != 200) {
          $('#bookView .alert').show();
        } else {
          $('#bookView').modal('hide');
        }
        return response.text();
      });
    return false;
  }

  function changePassword() {
    fetch('/api/v1/users/1/password', {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          oldPassword: $('#oldPassword').val(),
          newPassword: $('#newPassword').val(),
          passwordConfirmation: $('#passwordConfirmation').val()
        })
      }).then(function(response) {
        if (response.status != 200) {
          $('#changePasswordModal .alert').show();
        } else {
          $('#changePasswordModal').modal('hide');
        }
        return response.text();
      });;
  }

  function rating(bookId, currentRating) {
    return Array(10).fill()
      .map((item,i) => {
        var checked = i < currentRating ? 'text-primary' : 'text-muted';
        return `
          <span
            onclick="javascript:vote(${bookId}, ${i+1})"
            style="cursor:pointer"
            class="fa fa-star ${checked}">
          </span>`;
      })
      .join('');
  }

  function getBooks(s) {
    var url = new URL(window.location);
    url.pathname = '/api/v1/books';
    if (s) {
      url.search = new URLSearchParams({title:s, author:s}).toString();
    }
    fetch(url)
      .then((response) => { return response.json(); })
      .then((data) => {
        var w = $('#books').empty();
        data.forEach(book => {
          w.append(`
            <div class="col-sm-3 m-2">
              <div class="card">
                <div class="card-body">
                  <h5 class="card-title">${book.title} <a href="javascript:openBook(${book.id})"><i class="fa fa-link"></i></a></h5>
                  <p class="card-text">${book.author}</p>
                </div>
                <div class="card-footer text-muted">
                  ${rating(book.id, book.rating)}
                </div>
              </div>
            </div>
          `);
        });
      });
  }

});

function vote(bookId, rating) {
  console.log(bookId, rating);
  fetch(`/api/v1/books/${bookId}/ratings`, {
      method: 'post',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        rating: rating,
      })
    }).then(function(response) {
      console.log(response);
      return response.text();
    });
}

function openBook(bookId) {
  fetch(`/api/v1/books/${bookId}`)
    .then((response) => { return response.json(); })
    .then((book) => {
      $('#bookViewTitle').text(book.title)
      $('#bookViewAuthor').text(`By ${book.author} / ${book.year}y`);
      $('#reviewBookId').val(book.id);
      var comments = $('#bookViewComments').empty();
      book.commentList.forEach(comment => {
        comments.append(`
          <a href="#" class="list-group-item list-group-item-action active">
            <div class="d-flex w-100 justify-content-between">
              <h5>${comment.authorName}</h5>
              <small>${comment.time}</small>
            </div>
            <p class="mb-1">${comment.content}</p>
          </a>
        `);
      });
      $('#bookView').modal('show');
    });
}
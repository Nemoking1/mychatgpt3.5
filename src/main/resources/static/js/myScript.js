function saveToLocalStorage(message, response) {
    let chatHistory = JSON.parse(localStorage.getItem('chatHistory')) || [];
    chatHistory.push({ message: message, response: response });
    localStorage.setItem('chatHistory', JSON.stringify(chatHistory));
  }
function displayChatHistory() {
    let chatHistory = JSON.parse(localStorage.getItem('chatHistory')) || [];
    let chatHistoryDiv = document.getElementById('chatHistory');
    chatHistoryDiv.innerHTML = '';
    chatHistory.forEach(item => {
      chatHistoryDiv.innerHTML += `<p><strong>我说:</strong> ${item.message}</p><p><strong>AI回复:</strong> ${item.response}</p>`;
    });
  }

  function clearHistory() {
    localStorage.removeItem('chatHistory');
    displayChatHistory();
  }

  document.getElementById('chatForm').addEventListener('submit', function(e) {
    e.preventDefault();
    const message = document.getElementById('message').value;
    document.getElementById('message').value = ''; // 清空输入框
    // const response = 'AI回复内容'; // 这里应该是从 AI 返回的内容
    fetch('/chat?message=' + encodeURIComponent(message), {
      method: 'POST',
      headers: {
        'Content-Type': 'text/plain'
      }
    })
            .then(response => response.text())
            .then(data => {
              saveToLocalStorage(message, data);
              displayChatHistory();
            })
            .catch(error => {
              // 处理错误
            });
  });

  displayChatHistory();
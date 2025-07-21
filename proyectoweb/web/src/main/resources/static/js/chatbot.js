
    function mostrarChatbot() {
        const chatbot = document.getElementById('chatbot-flotante');
        chatbot.style.display = 'block';

        setTimeout(() => {
            chatbot.style.display = 'none';
        }, 30000);
    }

    setInterval(mostrarChatbot, 5 * 60 * 1000);
    setTimeout(mostrarChatbot, 10000); 


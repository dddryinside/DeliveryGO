document.addEventListener("DOMContentLoaded", function () {
    function startSlider() {
        const slider = document.getElementById('slider');
        if (!slider) return;

        let index = 0;
        const slides = document.querySelectorAll('.slide');
        const totalSlides = slides.length;

        setInterval(() => {
            index = (index + 1) % totalSlides;
            slider.style.transform = `translateX(${-index * 760}px)`;
        }, 3000);
    }

    setTimeout(startSlider, 1000);
});
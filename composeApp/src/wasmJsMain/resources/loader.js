(async function () {
    // Show loading screen immediately
    const loading = document.getElementById("loading");
    const app = document.body;

    // Load Compose JS dynamically
    const script = document.createElement("script");
    script.src = "composeApp.js";
    script.type = "application/javascript";

    // When WASM + Kotlin are ready
    script.onload = () => {
        loading.remove();
    };

    document.body.appendChild(script);
})();
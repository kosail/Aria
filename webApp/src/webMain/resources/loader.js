(async function () {
    // Show loading screen immediately
    const loading = document.getElementById("loading");

    // Load Compose JS dynamically
    const script = document.createElement("script");
    script.src = "webApp.js";
    script.type = "application/javascript";

    // When WASM + Kotlin are ready
    script.onload = () => {
        loading.remove();
    };

    document.body.appendChild(script);
})();

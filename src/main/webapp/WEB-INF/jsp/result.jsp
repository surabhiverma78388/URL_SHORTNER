<!DOCTYPE html>
<html>
<head>
    <title>Your Short Link</title>
    <style>
        body { font-family: Arial; text-align: center; margin-top: 100px; }
        .box { border: 1px solid #ddd; display: inline-block; padding: 20px; border-radius: 10px; background: #f9f9f9; }
        .stats { margin-top: 20px; color: #666; font-size: 0.9em; }
        a { color: #007bff; font-weight: bold; text-decoration: none; font-size: 1.2em; }
    </style>
</head>
<body>
    <div class="box">
        <h2>Link is ready!</h2>
        <p>Original: ${longUrl}</p>
        <p>Shortened Link: <a href="${shortUrl}" target="_blank">${shortUrl}</a></p>
        
        <hr>
        <div class="stats">
            <p><strong>Total Clicks:</strong> ${clicks}</p>
            
        </div>
        <br>
        <a href="/" style="font-size: 0.8em; color: gray;">Shorten another URL</a>
    </div>
</body>
</html>
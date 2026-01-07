<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>URL Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>All Shortened URLs</h2>
        <a href="/" class="btn btn-primary">Shorten New URL</a>
    </div>

    <table class="table table-striped table-hover border">
        <thead class="table-dark">
            <tr>
                <th>Original URL</th>
                <th>Short Link</th>
                <th>Total Clicks</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="url" items="${urls}">
                <tr>
                    <td class="text-truncate" style="max-width: 300px;">${url.longUrl}</td>
                    <td>
                        <a href="http://localhost:8081/${url.shortCode}" target="_blank">
                            localhost:8081/${url.shortCode}
                        </a>
                    </td>
                    <td><span class="badge bg-info text-dark">${url.clicks}</span></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
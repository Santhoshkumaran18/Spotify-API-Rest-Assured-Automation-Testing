Spotify API Automation Testing Framework 🎵
This project automates testing for the Spotify Playlist API using Rest Assured, TestNG, and Allure Reporting. It validates various playlist operations like creating, updating, retrieving, and deleting playlists. OAuth 2.0 authentication is used to securely access the Spotify API.

📌 Features
✅ Automated testing for Spotify Playlist API endpoints.
✅ OAuth 2.0 Token Management for authentication.
✅ Rest Assured for API testing.
✅ TestNG for test execution.
✅ Allure Reporting for visually appealing test reports.
✅ Data-Driven Testing using property files.
✅ Reusable API Requests & Specifications using RestResource and SpecBuilder.

🔧 Getting Started
1️⃣ Prerequisites
Ensure you have the following installed:

Java (JDK 11 or higher) – Download
Apache Maven – Download
Spotify Developer Account – Register Here
Allure Reporting (Optional) – Install with:

**npm install -g allure-commandline --save-dev**

2️⃣ Clone the Repository
git clone https://github.com/Santhoshkumaran18/Spotify-API-Rest-Assured-Automation-Testing.git
cd Spotify-API-Rest-Assured-Automation-Testing

3️⃣ Configure API Credentials
Go to the Spotify Developer Dashboard and create an app.
Retrieve the Client ID, Client Secret, and Refresh Token.
Add them to config.properties in src/test/resources/:

client_id=your_client_id
client_secret=your_client_secret
refresh_token=your_refresh_token
base_url=https://api.spotify.com/v1
user_id=your_spotify_user_id

4️⃣ Build the Project
Run the following command to install dependencies:
mvn clean install

5️⃣ Execute Tests
Run all tests using:
mvn test

Run specific test classes:
mvn test -Dtest=CreatePlaylistTest test

6️⃣ View Test Reports
To generate Allure Reports, run:
allure serve allure-results

📂 Project Structure
Spotify-API-Rest-Assured-Automation-Testing/
├── src/
│   ├── test/
│   │   ├── java/com/spotify/OAuth2/
│   │   │   ├── api/                # Handles API requests & responses
│   │   │   │   ├── RestResource.java
│   │   │   │   ├── Route.java
│   │   │   │   ├── SpecBuilder.java
│   │   │   │   ├── StatusCode.java
│   │   │   │   ├── TokenManager.java
│   │   │   │   └── applicationApi/
│   │   │   │       └── PlaylistApi.java
│   │   │   ├── models/              # POJOs for API responses
│   │   │   │   ├── Playlist.java
│   │   │   │   ├── Tracks.java
│   │   │   │   ├── Owner.java
│   │   │   │   ├── Error.java
│   │   │   │   ├── ExternalUrls.java
│   │   │   │   └── Followers.java
│   │   │   ├── tests/               # TestNG test cases
│   │   │   │   ├── BaseTest.java
│   │   │   │   ├── CreatePlaylistTest.java
│   │   │   │   ├── UpdatePlaylistTest.java
│   │   │   │   ├── GetPlaylistTest.java
│   │   │   │   └── DeletePlaylistTest.java
│   │   │   ├── utils/               # Utility classes
│   │   │   │   ├── DataLoader.java
│   │   │   │   └── ConfigLoader.java
│   │   │   └── resources/           # Config & test data
│   │   │       ├── config.properties
│   │   │       ├── data.properties
│   │   │       └── allure.properties
│   ├── resources/
├── pom.xml                          # Maven dependencies & build configurations
├── README.md

⚙️ Key Components
🔹 API Layer (api/)
RestResource.java – Handles HTTP requests (GET, POST, PUT, DELETE).
TokenManager.java – Manages authentication tokens.
SpecBuilder.java – Defines request specifications.
PlaylistApi.java – Implements API calls for playlist operations.

🔹 Models (models/)
Defines POJOs for API responses (Playlist, Tracks, Owner, etc.).

🔹 Test Cases (tests/)
CreatePlaylistTest.java – Tests playlist creation.
UpdatePlaylistTest.java – Tests updating a playlist.
GetPlaylistTest.java – Tests fetching a playlist.

🔹 Utilities (utils/)
ConfigLoader.java – Reads API credentials from config.properties.
DataLoader.java – Loads test data from data.properties.

🔹 Configuration (resources/)
config.properties – Stores API credentials & environment variables.
data.properties – Contains test data.

📢 Contributing
🚀 Feel free to contribute! Fork the repo, create a new branch, and submit a pull request.

📜 License
This project is licensed under the MIT License. See LICENSE for details.

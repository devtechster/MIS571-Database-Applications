# MIS571 DBApplications

## Make sure you have the following things:
- Latest Android Studio
- Java, JDK, JRE path set in environment variables
- Android simulator setup for respective versions

## Follow the steps below:

1. **Create a new project in Android Studio**
   - Select "Empty Views Activity".
   - Name your application something like `tutorial_xyz_yourname` (avoid using generic names like `MyApplication`).
   - After loading, it should show you support for both **Java** and **Kotlin**.

2. **If it's not showing Java+Kotlin support, follow these steps:**
   - Right-click on the `app` folder → **New** → **Activity** → **Empty Views Activity**.
   - Ensure you have only **one Empty Views Activity**.
   - Delete the `.kt` file (if created automatically), so you can work in **Java**.

3. **Otherwise, continue following the instructions:**
   - Create an `assets` folder in your project.
   - Paste the `.db` file into the `assets` folder.

4. **In the project:**
   - There are **2 Java files** written, but you can write **1000 files** if you want.
   - The goal is to achieve the output.

5. **Make sure to:**
   - In each Java file, ensure that the correct package path (e.g., `com.tutorial.yourname`) is declared at the top.

6. **Manifest and Gradle:**
   - Copy the **`AndroidManifest.xml`** file from this repo into your project.
   - Make the necessary **target API changes** in the `build.gradle (app)` file.

7. **Build and Run:**
   - After making the changes, give a build and then run the project.

# CI / GitHub Actions

This project includes a GitHub Actions workflow to build the Release APK and upload it as an artifact.

Required repository secrets (Settings → Secrets):

- `SIGNING_KEY_BASE64` — base64-encoded keystore file (`keystore.jks`). Optional, but if not provided CI will build unsigned APK.
- `KEYSTORE_PASSWORD` — password for the keystore.
- `KEY_ALIAS` — key alias.
- `KEY_PASSWORD` — key password.

How to create `SIGNING_KEY_BASE64` locally:

```bash
# encode keystore.jks to base64
base64 keystore.jks | pbcopy   # macOS (copies to clipboard)
base64 keystore.jks > keystore.jks.b64
```

Then add the base64 content to the `SIGNING_KEY_BASE64` secret.

CI workflow path: `.github/workflows/android-build.yml` — it sets up JDK 17, SDK/NDK, decodes the keystore, exports environment variables and runs `./gradlew assembleRelease`.

If you prefer not to use secrets, you can add `keystore.jks` to the repository (NOT RECOMMENDED) and set environment variables accordingly.

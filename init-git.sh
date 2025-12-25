#!/bin/bash
# Initialize and push ProtonHorizon to GitHub

set -e

echo "╔═══════════════════════════════════════════════════╗"
echo "║  ProtonHorizon - Initialize GitHub Repository   ║"
echo "╚═══════════════════════════════════════════════════╝"
echo ""

# Check if git is initialized
if [ ! -d ".git" ]; then
    echo "Initializing git repository..."
    git init
fi

# Configure git
echo ""
echo "Configuring git..."
git config user.email "your-email@example.com" || true
git config user.name "Your Name" || true

# Add all files
echo ""
echo "Adding files to git..."
git add -A

# Commit
echo ""
echo "Creating initial commit..."
git commit -m "Initial commit: ProtonHorizon v1.0.0" || echo "Repository already initialized"

# Create branches
echo ""
echo "Creating branches..."
git branch -D develop 2>/dev/null || true
git branch develop

# Show status
echo ""
echo "✅ Repository initialized!"
echo ""
echo "Next steps:"
echo ""
echo "1. Add remote:"
echo "   git remote add origin https://github.com/your-username/ProtonHorizon.git"
echo ""
echo "2. Push to GitHub:"
echo "   git branch -M main"
echo "   git push -u origin main"
echo "   git push -u origin develop"
echo ""
echo "3. Setup GitHub Secrets (Settings → Secrets):"
echo "   - See GITHUB_ACTIONS_SETUP.md"
echo ""
echo "4. Enable Actions:"
echo "   - Go to Settings → Actions → General"
echo "   - Select 'All actions and reusable workflows'"
echo ""
echo "5. Run first build:"
echo "   - Go to Actions → Build APK → Run workflow"
echo ""

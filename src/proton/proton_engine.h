#pragma once

#include <string>
#include <vector>

class ProtonEngine {
public:
    enum class WineVersion {
        WINE_STABLE,
        WINE_STAGING,
        WINE_GE
    };
    
    ProtonEngine();
    ~ProtonEngine();
    
    bool Initialize();
    void Shutdown();
    
    bool SetWineVersion(WineVersion version);
    bool SetEnvironmentVariable(const std::string& key, const std::string& value);
    bool ExecuteApplication(const std::string& appPath, const std::vector<std::string>& args);
    
    std::string GetWinePrefix() const;
    bool ConfigureWinePrefix();
    bool InstallDependencies(const std::vector<std::string>& dependencies);
    
private:
    std::string winePrefix;
    WineVersion currentVersion;
    bool initialized;
};

import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private var appModule: any AppModule = AppModuleImpl(parser: IOSVoiceToTextParser())
    
	var body: some Scene {
		WindowGroup {
            NavigationView {
                ContentView(appModule: appModule)
            }
		}
	}
}

//
//  IosAppApp.swift
//  IosApp
//
//  Created by Ненашев Никита Алексеевич on 25.04.2025.
//

import SwiftUI

@main
struct IosAppApp: App {
    lazy var root: RootComponent = DefaultRootComponent(
        componentContext: DefaultComponentContext(
            lifecycle: ApplicationLifecycle(),
            stateKeeper: stateKeeper,
            instanceKeeper: nil,
            backHandler: backDispatcher
        )
    )
    
    var body: some Scene {
        WindowGroup {
            root(root)
        }
    }
}

//@main
//struct iOSApp: App {
//    @UIApplicationDelegateAdaptor(AppDelegate.self)
//    var appDelegate: AppDelegate
//
//    var body: some Scene {
//        WindowGroup {
//            RootView(root: appDelegate.root, backDispatcher: appDelegate.backDispatcher)
//                .ignoresSafeArea(.all)
//        }
//    }
//}
//
//class AppDelegate: NSObject, UIApplicationDelegate {
//    private var stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: nil)
//    var backDispatcher: BackDispatcher = BackDispatcherKt.BackDispatcher()
//
//    lazy var root: RootComponent = DefaultRootComponent(
//        componentContext: DefaultComponentContext(
//            lifecycle: ApplicationLifecycle(),
//            stateKeeper: stateKeeper,
//            instanceKeeper: nil,
//            backHandler: backDispatcher
//        ),
//        featureInstaller: DefaultFeatureInstaller.shared,
//        deepLinkUrl: nil
//    )
//
//    func application(_ application: UIApplication, shouldSaveSecureApplicationState coder: NSCoder) -> Bool {
//        StateKeeperUtilsKt.save(coder: coder, state: stateKeeper.save())
//        return true
//    }
//    
//    func application(_ application: UIApplication, shouldRestoreSecureApplicationState coder: NSCoder) -> Bool {
////        stateKeeper = StateKeeperDispatcherKt.StateKeeperDispatcher(savedState: StateKeeperUtilsKt.restore(coder: coder))
//        return true
//    }
//}

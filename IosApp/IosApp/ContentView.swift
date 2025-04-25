//
//  ContentView.swift
//  IosApp
//
//  Created by Ненашев Никита Алексеевич on 25.04.2025.
//

import SwiftUI
import App

struct ContentView: View {
    @StateObject private var rootHolder = RootHolder()
    
    var body: some View {
        ComposeView(root: rootHolder.root)
            .onAppear { rootHolder.lifecycle.onCreate() }
            .onDisappear { rootHolder.lifecycle.onDestroy() }
    }
}

class RootHolder: ObservableObject {
    let lifecycle: LifecycleRegistry
    let root: RootComponent
    
    init() {
        lifecycle = LifecycleRegistry()
        root = RootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifecycle)
        )
    }
}

struct ComposeView: UIViewControllerRepresentable {
    let root: RootComponent
    
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController(root: root)
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

#Preview {
    ContentView()
}

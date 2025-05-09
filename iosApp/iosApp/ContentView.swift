import SwiftUI
import Shared

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel
    
    var body: some View {
        ListView(phrases: viewModel.messages).onAppear { self.viewModel.startObserving() }
    }
}

extension ContentView {
    @MainActor
    class ViewModel: ObservableObject {
        @Published var messages: Array<String> = []
        
        func startObserving() {
            Task {
                for await message in Messages().asFlow() {
                    self.messages.append(message)
                }
            }
        }
    }
}

struct ListView: View {
    let phrases: Array<String>
    
    var body: some View {
        List(phrases, id: \.self) {
            Text($0)
        }
    }
}

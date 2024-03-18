//
//  MicrophonePowerObserver.swift
//  iosApp
//
//  Created by João Lucas Woigt Azevedo on 19/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared
import Speech
import Combine

class MicrophonePowerObserver: ObservableObject {
    private var cancellable: AnyCancellable? = nil
    private var audioRecorder: AVAudioRecorder? = nil
    
    @Published private(set) var micPowerRation = 0.0
    private let powerRatioEmissionPerSecond = 20.0
    
    func starObserving() {
        do {
            let recorderSettings: [String: Any] = [
                AVFormatIDKey: NSNumber(value: kAudioFormatAppleLossless),
                AVNumberOfChannelsKey: 1,
            ]
            
            let recorder = try AVAudioRecorder(
                url: URL(fileURLWithPath: "/dev/null", isDirectory: true),
                settings: recorderSettings
            )
            recorder.isMeteringEnabled = true
            recorder.record()
            self.audioRecorder = recorder
            
            self.cancellable = Timer.publish(
                every: 1.0 / powerRatioEmissionPerSecond,
                tolerance: 1.0 / powerRatioEmissionPerSecond,
                on: .main,
                in: .common
            )
            .autoconnect()
            .sink {[weak self] _ in
                recorder.updateMeters()
                let powerOffset = recorder.averagePower(forChannel: 0)
                
                if powerOffset < -50 {
                    self?.micPowerRation = 0.0
                } else {
                    let normalizedOffset = CGFloat(50 + powerOffset) / 50
                    self?.micPowerRation = normalizedOffset
                }
            }
        } catch {
            print("An error occurred when observing microphone power: \(error.localizedDescription)")
        }
    }
    
    func release() {
        cancellable = nil
        
        audioRecorder?.stop()
        audioRecorder = nil
        
        micPowerRation = 0.0
    }
}

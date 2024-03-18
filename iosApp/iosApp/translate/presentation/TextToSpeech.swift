//
//  TextToSpeech.swift
//  iosApp
//
//  Created by João Lucas Woigt Azevedo on 17/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import AVFoundation

struct TextToSpeech {
    private let synthesizer = AVSpeechSynthesizer()
    
    func speak(text: String, language: String) {
        let utterence = AVSpeechUtterance(string: text)
        utterence.voice = AVSpeechSynthesisVoice(language: language)
        utterence.volume = 1
        synthesizer.speak(utterence)
    }
}

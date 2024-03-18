//
//  LanguageDropDown.swift
//  iosApp
//
//  Created by João Lucas Woigt Azevedo on 13/02/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct LanguageDropDown: View {
    var language: UiLanguage
    var isOpen: Bool
    var selectLanguage: (UiLanguage) -> Void
    
    var body: some View {
        Menu {
            VStack {
                ForEach(UiLanguage.Companion().allLanguages, id: \.self.language.langCode) { langauge in
                    LanguageDropDownItem(
                        language: langauge,
                        onClick: {
                            selectLanguage(langauge)
                        }
                    )
                }
            }
        } label: {
            HStack {
                SmallLanguageIcon(language: language)
                Text(language.language.langName)
                    .foregroundColor(.lightBlue)
                Image(systemName: isOpen ? "chevron.up" : "chevron.down")
                    .foregroundColor(.lightBlue)
            }
            }
        }
        
    }


struct LanguageDropDown_Previews: PreviewProvider {
    static var previews: some View {
        LanguageDropDown(language: UiLanguage(language: .german, imageName: "german"), isOpen: true, selectLanguage: {language in})
    }
}

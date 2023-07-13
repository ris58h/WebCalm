<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# WebCalm Changelog

## [Unreleased]

### Added
- [JavaScript] References from `querySelector`, `querySelectorAll`, `closest`, `matches` and `getElementById` on elements with id. 

## [0.10] - 2023-07-10

### Added
- [CSS] Show named colors in completion.
- [CSS] Show colors with color-picker in gutter for named-colors, hex-colors and `rgb`.
- [CSS] More suggestions in property values completion.
- [CSS] At-keyword completion.

## [0.9] - 2023-07-03

### Added
- CSS support.

## [0.8] - 2023-06-28

### Added
- Completion for `if`, `while`, `for`, `switch` and `try`, `catch`, `finally`.
- Plugin's icon.
- Formatting.

### Fixed
- Highlight `static` keyword in non-strict mode.
- Highlight `get` keyword.

## [0.7] - 2023-06-21

### Added
- Static class block.
- Semantic Highlighting for JavaScript.

### Fixed
- Class elements not parsed properly (9be1cc78 regression).

## [0.6] - 2023-06-14

### Added
- External script support (`src` in `script` tag).
- JavaScript File template.

### Fixed
- Support static class fields. See https://github.com/antlr/grammars-v4/issues/3451
- Support class fields without initializer. See https://github.com/antlr/grammars-v4/issues/3474
- Support string literals in import/export statements. See https://github.com/antlr/grammars-v4/issues/3484
- Inplace renaming for labels and iteration statement variables.

## [0.5] - 2023-06-07

### Added
- Inplace renaming.
- GitHub build workflow.
- Return completion.
- Label reference.
- Right-hand completion.

### Fixed
- `from` can be used as identifier. See https://github.com/antlr/grammars-v4/issues/3516
- Fix JavaScript Lexer exception ([#1](https://github.com/ris58h/WebCalm/issues/1)).
- Find usages fix.
- Words scanner and syntax highlighting issues fix.

## [0.4] - 2023-05-30

### Added
- Quote handler.
- Statement completion.
- Rest parameters support.
- Switch and class declaration folding.
- `yield`, `with`, `debugger`, `switch`.
- `try`, `catch` and `throw`.
- Labeled block, `break` and `continue`.

## [0.3] - 2023-05-26

### Added
- Recursive call line marker provider.
- Go to declaration for recursion function call.
- Regex literal.
- Class fields and methods.

### Fixed
- Remove unused jars from resulting distribution zip.
- Grammar fix. See https://github.com/antlr/grammars-v4/issues/3387

## [0.2] - 2023-05-23

### Added
- Optional XML dependency.
- Class declaration.
- Structure view.
- Find usages in related JavaScript scripts.

## [0.1] - 2023-05-17

### Added
- Injected scripts in HTML.
- Initial JavaScript support.

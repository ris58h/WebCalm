<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# WebCalm Changelog

## [Unreleased]

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

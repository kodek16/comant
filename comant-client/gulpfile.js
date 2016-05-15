'use strict';

let gulp = require('gulp');
let sass = require('gulp-sass');
let autoprefixer = require('gulp-autoprefixer');

let argv = require('yargs').argv;
let rimraf = require('rimraf');

let buildDir = argv.dest ? argv.dest : 'dist';

gulp.task('clean', (done) => {
  rimraf(buildDir, done);
});

///////
// HTML

gulp.task('html', () => {
  return gulp.src('src/index.html')
    .pipe(gulp.dest(buildDir));
});

gulp.task('html:watch', () => {
  gulp.watch('src/index.html', ['html']);
});

///////
// Sass

let sassPaths = [
  'bower_components/foundation-sites/scss',
  'bower_components/motion-ui/src'
];

gulp.task('sass', () => {
  return gulp.src('src/app.scss')
    .pipe(sass({
      includePaths: sassPaths
    })
    .on('error', sass.logError))
    .pipe(autoprefixer({
      browsers: ['last 2 versions', 'ie >= 9']
    }))
    .pipe(gulp.dest(buildDir));
});

gulp.task('sass:watch', () => {
  gulp.watch('src/**/*.scss', ['sass']);
});

///////////////////
// Bower components

gulp.task('bower-components', () => {
  return gulp.src('bower_components/**')
    .pipe(gulp.dest(buildDir + '/bower_components'));
});

gulp.task('bower-components:watch', () => {
  gulp.watch('bower_components', ['bower-components']);
});

gulp.task('build', ['html', 'sass', 'bower-components']);

gulp.task('watch', ['build', 'html:watch', 'sass:watch', 'bower-components:watch']);

gulp.task('default', ['watch']);
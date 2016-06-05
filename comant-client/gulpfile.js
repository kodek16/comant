'use strict';

let gulp = require('gulp');
let sass = require('gulp-sass');
let autoprefixer = require('gulp-autoprefixer');
let babel = require('gulp-babel');
let concat = require('gulp-concat');

let argv = require('yargs').argv;
let rimraf = require('rimraf');

let buildDir = argv.dest ? argv.dest : 'dist';

gulp.task('clean', (done) => {
  rimraf(buildDir, done);
});

///////
// HTML

gulp.task('html', () => {
  return gulp.src('src/**/*.html')
    .pipe(gulp.dest(buildDir));
});

gulp.task('html:watch', () => {
  gulp.watch('src/**/*.html', ['html']);
});

///////
// Sass

let sassPaths = [
  'bower_components/foundation/scss',
  'bower_components/font-awesome/scss'
];

gulp.task('sass', () => {
  return gulp.src('src/**/*.scss')
    .pipe(concat('app.scss'))
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

////
//JS

gulp.task('js', () => {
  return gulp.src('src/**/*.js')
    .pipe(babel({
      presets: ['es2015']
    }))
    .on('error', function(err) {
      console.log(err);
      this.emit('end');
    })
    .pipe(concat('app.js'))
    .pipe(gulp.dest(buildDir))
});

gulp.task('js:watch', () => {
  gulp.watch('src/**/*.js', ['js']);
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

gulp.task('build', ['html', 'sass', 'js', 'bower-components']);

gulp.task('watch', ['build', 'html:watch', 'sass:watch', 'js:watch', 'bower-components:watch']);

gulp.task('default', ['watch']);
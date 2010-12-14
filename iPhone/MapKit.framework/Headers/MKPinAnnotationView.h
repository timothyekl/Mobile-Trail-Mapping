//
//  MKPinAnnotationView.h
//  MapKit
//
//  Copyright 2009-2010 Apple Inc. All rights reserved.
//

#import <MapKit/MKAnnotationView.h>

enum {
    MKPinAnnotationColorRed = 0,
    MKPinAnnotationColorGreen,
    MKPinAnnotationColorPurple
};
typedef NSUInteger MKPinAnnotationColor;

@class MKPinAnnotationViewInternal;

@interface MKPinAnnotationView : MKAnnotationView
{
@private
    MKPinAnnotationViewInternal *_pinInternal;
}

@property (nonatomic) MKPinAnnotationColor pinColor;

@property (nonatomic) BOOL animatesDrop;

@end
